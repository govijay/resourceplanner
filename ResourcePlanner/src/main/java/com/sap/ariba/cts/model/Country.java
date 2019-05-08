package com.sap.ariba.cts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Country Model.
 *
 * <p> Country Entity Class.</p>
 *
 * @author Vijay.G
 * @version 1.0
 */

@Entity
@Table(name = "COUNTRY")
@SequenceGenerator(name = "default_gen", sequenceName = "country_seq", allocationSize = 1)
public class Country extends BaseEntity {

  @Column(name = "COUNTRY_CODE")
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

  public Country() {
  }

  public Country(@NotBlank String countryCode, @NotBlank String countryName, Region regionCode) {
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.regionCode = regionCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public Region getRegionCode() {
    return regionCode;
  }

  public void setRegionCode(Region regionCode) {
    this.regionCode = regionCode;
  }

  public Collection<City> getListOfCity() {
    return listOfCity;
  }

  public void setListOfCity(Collection<City> listOfCity) {
    this.listOfCity = listOfCity;
  }

  public void addCity(City city) {
    listOfCity.add(city);
    city.setCountry(this);

  }

  public void removeCity(City city) {
    listOfCity.remove(city);
    city.setCountry(null);
  }
}
