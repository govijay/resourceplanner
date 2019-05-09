package com.sap.ariba.cts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * City Model.
 *
 * <p> City Entity Class</p>
 *
 * @author Vijay.G
 * @version 1.0
 */

@Entity
@Table(name = "CITY")
@SequenceGenerator(name = "default_gen", sequenceName = "city_seq", allocationSize = 1)
public class City extends BaseEntity {

  @Column(name = "CITY_CODE")
  @NotBlank
  private String cityCode;

  @Column(name = "CITY_NAME")
  @NotBlank
  private String cityName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COUNTRY_CODE", nullable = false)
  private Country country;

  public City() {
  }

  public City(@NotBlank String cityCode, @NotBlank String cityName, Country country) {
    this.cityCode = cityCode;
    this.cityName = cityName;
    this.country = country;
  }

  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
}
