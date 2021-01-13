package com.devonfw.application.mtsj.dishmanagement.dataaccess.api;

import java.math.BigDecimal;
import java.util.List;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.dishmanagement.common.api.Dish;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.imagemanagement.dataaccess.api.ImageEntity;
import com.devonfw.application.mtsj.xap.BeanUtils;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceExclude;

/**
 * The {@link com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity persistent entity} for
 * {@link Dish}.
 */

@SpaceClass
public class DishEntity extends ApplicationPersistenceEntity implements Dish {

  private static final long serialVersionUID = 1L;

  private String name;

  private String description;

  private BigDecimal price;

  private ImageEntity image;

  private List<IngredientEntity> extras;

  private List<CategoryEntity> categories;

  /**
   * @return name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getName}.
   */
  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return description
   */
  @Override
  public String getDescription() {

    return this.description;
  }

  /**
   * @param description new value of {@link #getDescription}.
   */
  @Override
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * @return price
   */
  @Override
  public BigDecimal getPrice() {

    return this.price;
  }

  /**
   * @param price new value of {@link #getPrice}.
   */
  @Override
  public void setPrice(BigDecimal price) {

    this.price = price;
  }

  /**
   * The image will get queried from the space based on the stored imageId inside the space, using the setImageId
   * method.
   * 
   * @return image
   */

  @SpaceExclude
  public ImageEntity getImage() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    ImageEntity queriedImage = gigaspace.readById(ImageEntity.class, getImageId());
    return queriedImage;
  }

  /**
   * @param image new value of {@link #getImage}.
   */
  public void setImage(ImageEntity image) {

    this.image = image;
  }

  /**
   * @return extras
   */
  public List<IngredientEntity> getExtras() {

    return this.extras;
  }

  /**
   * @param extras new value of {@link #getExtras}.
   */
  public void setExtras(List<IngredientEntity> extras) {

    this.extras = extras;
  }

  /**
   * @return categories
   */
  public List<CategoryEntity> getCategories() {

    return this.categories;
  }

  /**
   * @param categories new value of {@link #getCategories}.
   */
  public void setCategories(List<CategoryEntity> categories) {

    this.categories = categories;
  }

  @Override
  public String getImageId() {

    if (this.image == null) {
      return null;
    }
    return this.image.getId();
  }

  @Override
  public void setImageId(String imageId) {

    if (imageId == null) {
      this.image = null;
    } else {
      ImageEntity imageEntity = new ImageEntity();
      imageEntity.setId(imageId);
      this.image = imageEntity;
    }
  }

}
