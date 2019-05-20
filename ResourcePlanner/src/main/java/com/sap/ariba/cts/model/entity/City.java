package com.sap.ariba.cts.model.entity;

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
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sap.ariba.cts.model.base.BaseEntity;

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
public class City extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citySequenceGenerator")
  @GenericGenerator(name = "citySequenceGenerator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {
                  @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "city_seq"),
                  @Parameter(name = SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, value = "_CTY"),
                  @Parameter(name = SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, value = "true"),
                  @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;


  @Column(name = "CITY_CODE",unique = true)
  @NotBlank
  private String cityCode;

  @Column(name = "CITY_NAME")
  @NotBlank
  private String cityName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COUNTRY_BASEID")
  @JsonBackReference
  private Country countryBaseId;

  /**
   * Instantiates a new City.
   */
  protected City() {
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
    return countryBaseId;
  }

  /**
   * Sets country.
   *
   * @param countryBaseId the country
   */
  public void setCountry(Country countryBaseId) {
    this.countryBaseId = countryBaseId;
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
