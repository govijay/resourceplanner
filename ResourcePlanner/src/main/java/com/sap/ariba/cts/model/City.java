package com.sap.ariba.cts.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;
import com.sap.ariba.cts.model.support.ClassMetaProperty;

/**
 * City Model.
 *
 * <p> City Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "CITY")
@ClassMetaProperty(code = "CTY")
public class City extends BaseEntity {

  @Column(name = "CITY_CODE",unique = true)
  @NotBlank
  private String cityCode;

  @Column(name = "CITY_NAME")
  @NotBlank
  private String cityName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COUNTRY_CODE", nullable = false)
  private Country country;

  /**
   * Instantiates a new City.
   */
  public City() {
  }

  /**
   * Instantiates a new City.
   *
   * @param cityCode the city code
   * @param cityName the city name
   * @param country  the country
   */
  public City(@NotBlank String cityCode, @NotBlank String cityName, Country country) {
    this.cityCode = cityCode;
    this.cityName = cityName;
    this.country = country;
  }

  @Override
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ctrySequenceGenerator")
  @GenericGenerator(name = "ctrySequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "ctry_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  public String getBaseId() {
    return super.getBaseId();
  }

  /**
   * Gets city code.
   *
   * @return the city code
   */
  public String getCityCode() {
    return cityCode;
  }

  /**
   * Sets city code.
   *
   * @param cityCode the city code
   */
  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  /**
   * Gets city name.
   *
   * @return the city name
   */
  public String getCityName() {
    return cityName;
  }

  /**
   * Sets city name.
   *
   * @param cityName the city name
   */
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  /**
   * Gets country.
   *
   * @return the country
   */
  public Country getCountry() {
    return country;
  }

  /**
   * Sets country.
   *
   * @param country the country
   */
  public void setCountry(Country country) {
    this.country = country;
  }
}
