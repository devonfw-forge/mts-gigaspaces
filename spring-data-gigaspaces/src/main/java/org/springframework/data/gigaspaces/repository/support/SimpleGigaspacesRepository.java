package org.springframework.data.gigaspaces.repository.support;

import com.gigaspaces.query.IdQuery;
import com.gigaspaces.query.IdsQuery;
import com.gigaspaces.query.aggregators.AggregationSet;
import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.gigaspaces.repository.GigaspacesRepository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.gigaspaces.repository.query.Projection;
import org.springframework.data.gigaspaces.repository.query.QueryBuilder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static org.springframework.data.gigaspaces.querydsl.GigaspacesQueryDslUtils.*;
/**
 * @author Oleksiy_Dyagilev
 */
public class SimpleGigaspacesRepository<T, ID extends Serializable> implements GigaspacesRepository<T, ID> {
    protected GigaSpace space;
    protected EntityInformation<T, ID> entityInformation;

    public SimpleGigaspacesRepository(GigaSpace space, EntityInformation<T, ID> entityInformation) {
        this.space = space;
        this.entityInformation = entityInformation;
    }

    @Override
    public GigaSpace space() {
        return space;
    }

    @Override
    public <S extends T> S save(S entity) {
        space.write(entity);
        return entity;
    }

    @Override
    public <S extends T> LeaseContext<S> save(S entity, long lease, TimeUnit unit) {
        Assert.notNull(unit, "unit must not be null");
        return space.write(entity, unit.toMillis(lease));
    }

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        space.writeMultiple(toArray(entities));
        return entities;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(findOneInternal(id, null));
    }

    @Override
    public boolean existsById(ID id) {
        return findById(id).isPresent();//Todo : better solution or projection
    }

    @Override
    public <S extends T> Iterable<LeaseContext<S>> save(Iterable<S> entities, long lease, TimeUnit unit) {
        Assert.notNull(unit, "unit must not be null");
        LeaseContext<S>[] leaseContexts = space.writeMultiple(toArray(entities), unit.toMillis(lease));
        return Arrays.asList(leaseContexts);
    }

    @Override
    public T takeOne(ID id) {
        Class<T> aClass = entityInformation.getJavaType();
        return space.takeById(aClass, id);
    }

    @Override
    public Iterable<T> takeAll(Iterable<ID> ids) {
        Class<T> aClass = entityInformation.getJavaType();
        return space.takeByIds(aClass, toArray(ids));
    }
    
    @Override
    public Iterable<T> takeAll() {
        Class<T> aClass = entityInformation.getJavaType();
        T t;
        try {
            t = aClass.newInstance();
            return Arrays.asList(space.takeMultiple(t));
        } catch (InstantiationException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public T findOne(ID id, Projection projection) {
        return findOneInternal(id, projection);
    }

    @Override
    public T findOne(ID id) {
        return findOneInternal(id, null);
    }

    private T findOneInternal(ID id, Projection projection) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        IdQuery<T> idQuery = idQuery(id);

        if (projection != null) {
            idQuery.setProjections(projection.getProperties());
        }

        return space.read(idQuery);
    }


    @Override
    public Iterable<T> findAll() {
        return findAllSortedInternal(null, null, null);
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return findAllByIdsInternal(ids, null);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids, Projection projection) {
        return findAllByIdsInternal(ids, projection);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        return findAllByIdsInternal(ids, null);
    }

    private Iterable<T> findAllByIdsInternal(Iterable<ID> ids, Projection projection) {
        IdsQuery<T> query = idsQuery(toArray(ids));
        if (projection != null) {
            query.setProjections(projection.getProperties());
        }
        return space.readByIds(query);
    }

    @Override
    public Iterable<T> findAll(Projection projection) {
        return findAllSortedInternal(projection, null, null);
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        return findAllSortedInternal(null, sort, null);
    }

    @Override
    public Iterable<T> findAll(Sort sort, Projection projection) {
        return findAllSortedInternal(projection, sort, null);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return new PageImpl<>(findAllSortedInternal(null, null, pageable));
    }

    @Override
    public Page<T> findAll(Pageable pageable, Projection projection) {
        return new PageImpl<>(findAllSortedInternal(projection, null, pageable));
    }

    private List<T> findAllSortedInternal(Projection projection, Sort sort, Pageable pageable) {
        StringBuilder stringBuilder = new StringBuilder("");

        // paging or sorting
        if (isPaged(pageable)) {
            stringBuilder.append(new QueryBuilder(pageable).buildQuery());
        } else if (isSorted(sort)) {
            stringBuilder.append(new QueryBuilder(sort).buildQuery());
        }

        SQLQuery<T> query = sqlQuery(stringBuilder.toString());

        // projection
        if (projection != null) {
            query.setProjections(projection.getProperties());
        }

        T[] found = space.readMultiple(query);
        return new ArrayList<>(Arrays.asList(found));
    }

    @Override
    public long count() {
        SQLQuery<T> query = sqlQuery("");
        return space.aggregate(query, new AggregationSet().count("")).getLong(0);
    }

    @Override
    public void deleteById(ID id) {
        IdQuery<T> idQuery = idQuery(id).setProjections("");
        space.takeById(idQuery);
    }

    @Override
    public void delete(T entity) {
        deleteById(entityInformation.getId(entity));
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        // TODO: consider replacing with distributed task to not return deleted entities back on client
        List<ID> idList = new ArrayList<>();
        for (T entity : entities) {
            ID id = entityInformation.getId(entity);
            idList.add(id);
        }
        IdsQuery<T> idsQuery = idsQuery(idList.toArray()).setProjections("");
        space.takeByIds(idsQuery);
    }

    @Override
    public void deleteAll() {
        // TODO: consider replacing with distributed task to not return deleted entities back on client
        SQLQuery<T> query = sqlQuery("").setProjections("");
        space.takeMultiple(query);
    }

    @SuppressWarnings("unchecked")
    protected <E> E[] toArray(Iterable<E> elements) {
        List<E> arrayList = new LinkedList<>();
        for (E element : elements) {
            arrayList.add(element);
        }
        return (E[]) arrayList.toArray();
    }

    protected IdQuery<T> idQuery(ID id) {
        return new IdQuery<>(entityInformation.getJavaType(), id);
    }

    protected IdsQuery<T> idsQuery(Object[] ids) {
        return new IdsQuery<>(entityInformation.getJavaType(), ids);
    }

    protected SQLQuery<T> sqlQuery(String query) {
        return new SQLQuery<>(entityInformation.getJavaType(), query);
    }
}
