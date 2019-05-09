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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;
import com.sap.ariba.cts.model.support.ClassMetaProperty;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Country Model.
 *
 * <p> Country Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "COUNTRY")
@ClassMetaProperty(code = "CTRY")
public class Country extends BaseEntity {

  @Column(name = "COUNTRY_CODE",unique = true)
  @NotBlank
  private String countryCode;

  @Column(name = "COUNTRY_NAME")
  @NotBlank
  private String countryName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "REGION_CODE", nullable = false)
  private Region regionCode;

  @OneToMany(mappedBy = "country")
  private Collection<City> listOfCity = new ArrayList<>();

  /**
   * Instantiates a new Country.
   */
  public Country() {
  }

  /**
   * Instantiates a new Country.
   *
   * @param countryCode the country code
   * @param countryName the country name
   * @param regionCode  the region code
   */
  public Country(@NotBlank String countryCode, @NotBlank String countryName, Region regionCode) {
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.regionCode = regionCode;
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
   * Gets country code.
   *
   * @return the country code
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Sets country code.
   *
   * @param countryCode the country code
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * Gets country name.
   *
   * @return the country name
   */
  public String getCountryName() {
    return countryName;
  }

  /**
   * Sets country name.
   *
   * @param countryName the country name
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  /**
   * Gets region code.
   *
   * @return the region code
   */
  public Region getRegionCode() {
    return regionCode;
  }

  /**
   * Sets region code.
   *
   * @param regionCode the region code
   */
  public void setRegionCode(Region regionCode) {
    this.regionCode = regionCode;
  }

  /**
   * Gets list of city.
   *
   * @return the list of city
   */
  public Collection<City> getListOfCity() {
    return listOfCity;
  }

  /**
   * Sets list of city.
   *
   * @param listOfCity the list of city
   */
  public void setListOfCity(Collection<City> listOfCity) {
    this.listOfCity = listOfCity;
  }

  /**
   * Add city.
   *
   * @param city the city
   */
  public void addCity(City city) {
    listOfCity.add(city);
    city.setCountry(this);

  }

  /**
   * Remove city.
   *
   * @param city the city
   */
  public void removeCity(City city) {
    listOfCity.remove(city);
    city.setCountry(null);
  }
}
