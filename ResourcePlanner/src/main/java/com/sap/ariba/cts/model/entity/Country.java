package com.sap.ariba.cts.model.entity;

import javax.persistence.CascadeType;
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
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sap.ariba.cts.model.base.BaseEntity;

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
@Table(name = "COUNTRIES")
public class Country extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ctrySequenceGenerator")
  @GenericGenerator(name = "ctrySequenceGenerator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {
                  @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "ctry_seq"),
                  @Parameter(name = SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, value = "_CTRY"),
                  @Parameter(name = SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, value = "true"),
                  @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "COUNTRY_CODE",unique = true)
  @NotBlank
  private String countryCode;

  @Column(name = "COUNTRY_NAME")
  @NotBlank
  private String countryName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "REGION_BASEID")
  @JsonBackReference
  private Region regionBaseId;

  @OneToMany(mappedBy = "countryBaseId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonManagedReference
  private Collection<City> cities;

  /**
   * Instantiates a new Country.
   */
  protected Country() {
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
    return regionBaseId;
  }

  /**
   * Sets region code.
   *
   * @param regionBaseId the region code
   */
  public void setRegionCode(Region regionBaseId) {
    this.regionBaseId = regionBaseId;
  }

  /**
   * Gets base id.
   *
   * @return the base id
   */
  public String getBaseId() {
    return baseId;
  }

  /**
   * Sets base id.
   *
   * @param baseId the base id
   */
  public void setBaseId(String baseId) {
    this.baseId = baseId;
  }

  /**
   * Gets cities.
   *
   * @return the cities
   */
  public Collection<City> getCities() {
    return cities;
  }

  /**
   * Sets cities.
   *
   * @param cities the cities
   */
  public void setCities(Collection<City> cities) {
    this.cities = cities;
  }

  @Override
  public String toString() {
    return "Country{" +
            "baseId='" + baseId + '\'' +
            ", countryCode='" + countryCode + '\'' +
            ", countryName='" + countryName + '\'' +
            ", regionBaseId=" + regionBaseId +
            '}';
  }
}
