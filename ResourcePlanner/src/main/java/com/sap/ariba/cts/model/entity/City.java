package com.sap.ariba.cts.model.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.CityDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

/**
 * City Model.
 *
 * <p> City Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "CITIES")
@ClassMetaProperty(code = "CTY")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class City extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citySequenceGenerator")
  @GenericGenerator(name = "citySequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "city_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;


  @Column(name = "CITY_CODE")
  private String cityCode;

  @Column(name = "CITY_NAME")
  private String cityName;

  @ManyToOne
  @JoinColumn(name = "COUNTRY_BASEID")
  @JsonBackReference
  private Country country;

  @Transient
  private String countryBaseId;

  /**
   * Instantiates a new City.
   */
  protected City() {
  }

  /**
   * Instantiates a new Base entity.
   *
   * @param active the active
   */
  public City(boolean active, String baseId, String cityCode, String cityName, String countryBaseId) {
    super(active);
    this.baseId = baseId;
    this.cityCode = cityCode;
    this.cityName = cityName;
    this.countryBaseId = countryBaseId;
  }

  public String getBaseId() {
    return baseId;
  }

  public void setBaseId(String baseId) {
    this.baseId = baseId;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
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

  public void setCountryBaseId(String countryBaseId) {
    this.countryBaseId = countryBaseId;
  }

  public String getCountryBaseId() {
    return countryBaseId;
  }

  public static City toEntity(CityDto cityDto) {
    return new City(
            cityDto.isActive(),
            cityDto.getBaseId(),
            cityDto.getCtyCode(),
            cityDto.getCtyName(),
            cityDto.getCountryId());

  }

  @Override
  public String toString() {
    return "City{" +
            "baseId='" + baseId + '\'' +
            ", cityCode='" + cityCode + '\'' +
            ", cityName='" + cityName + '\'' +
            '}';
  }
}
