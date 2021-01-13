package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import java.io.Serializable;
import java.sql.Timestamp;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.annotation.pojo.SpaceVersion;
import com.gigaspaces.metadata.index.SpaceIndexType;

/**
 * @author dpatesan
 *
 */
@SpaceClass
public class DateInfoEntity implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String id;

  private Timestamp date;

  private Double temperature;

  private String designation;

  private Integer versionId;

  @SpaceVersion
  public Integer getVersionId() {

    return this.versionId;
  }

  public void setVersionId(Integer versionId) {

    this.versionId = versionId;
  }

  /**
   * @return id
   */
  @SpaceId(autoGenerate = true)
  @SpaceRouting
  public String getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(String id) {

    this.id = id;
  }

  /**
   * @return date
   */
  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public Timestamp getDate() {

    return this.date;
  }

  /**
   * @param date new value of {@link #getdate}.
   */
  public void setDate(Timestamp date) {

    this.date = date;
  }

  /**
   * @return temperature
   */
  public Double getTemperature() {

    return this.temperature;
  }

  /**
   * @param temperature new value of {@link #gettemperature}.
   */
  public void setTemperature(Double temperature) {

    this.temperature = temperature;
  }

  /**
   * @return designation
   */
  public String getDesignation() {

    return this.designation;
  }

  /**
   * @param designation new value of {@link #getdesignation}.
   */
  public void setDesignation(String designation) {

    this.designation = designation;
  }

}
