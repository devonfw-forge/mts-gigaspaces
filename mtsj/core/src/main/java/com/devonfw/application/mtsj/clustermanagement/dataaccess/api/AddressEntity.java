package com.devonfw.application.mtsj.clustermanagement.dataaccess.api;

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.annotation.pojo.SpaceVersion;
import com.gigaspaces.metadata.index.SpaceIndexType;

public class AddressEntity {
  private String id;

  private String userId;

  private String street;

  private String housenumber;

  private String plz;

  private String city;

  private String country;

  private Double latitude;

  private Double longitude;

  private Integer versionId;

  @SpaceId(autoGenerate = true)
  @SpaceRouting
  public String getId() {

    return this.id;
  }

  public void setId(String id) {

    this.id = id;
  }

  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public String getUserId() {

    return this.userId;
  }

  public void setUserId(String userId) {

    this.userId = userId;
  }

  public String getStreet() {

    return this.street;
  }

  public void setStreet(String street) {

    this.street = street;
  }

  public String getHousenumber() {

    return this.housenumber;
  }

  public void setHousenumber(String housenumber) {

    this.housenumber = housenumber;
  }

  public String getPlz() {

    return this.plz;
  }

  public void setPlz(String plz) {

    this.plz = plz;
  }

  public String getCity() {

    return this.city;
  }

  public void setCity(String city) {

    this.city = city;
  }

  public String getCountry() {

    return this.country;
  }

  public void setCountry(String country) {

    this.country = country;
  }

  public Double getLatitude() {

    return this.latitude;
  }

  public void setLatitude(Double latitude) {

    this.latitude = latitude;
  }

  public Double getLongitude() {

    return this.longitude;
  }

  public void setLongitude(Double longitude) {

    this.longitude = longitude;
  }

  @SpaceVersion
  public Integer getVersionId() {

    return this.versionId;
  }

  public void setVersionId(Integer versionId) {

    this.versionId = versionId;
  }

}
