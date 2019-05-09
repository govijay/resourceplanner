package com.sap.ariba.cts.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Region Entity Object.
 *
 * <p>Top level object in the organizational hierarchy like
 * NA,LAC,EMEA,APAC</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "REGION")
@ClassMetaProperty(code = "REG")
public class Region extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "REGION_CODE",unique = true)
  @NotBlank
  private String regionCode;

  @Column(name = "REGION_NAME")
  @NotBlank
  private String regionName;

  @OneToMany(mappedBy = "regionCode")
  private Collection<Country> listOfCountry = new ArrayList<>();

  @OneToMany(mappedBy = "regionCode")
  private Collection<Department> listOfDepartment = new ArrayList<>();

  /**
   * Instantiates a new Region.
   */
  public Region() {
  }

  /**
   * Instantiates a new Region.
   *
   * @param regionCode the region code
   * @param regionName the region name
   */
  public Region(@NotBlank String regionCode, @NotBlank String regionName) {
    this.regionCode = regionCode;
    this.regionName = regionName;
  }

  /**
   * Instantiates a new Region.
   *
   * @param regionCode       the region code
   * @param regionName       the region name
   * @param listOfCountry    the list of country
   * @param listOfDepartment the list of department
   */
  public Region(@NotBlank String regionCode, @NotBlank String regionName, Collection<Country> listOfCountry, Collection<Department> listOfDepartment) {
    this.regionCode = regionCode;
    this.regionName = regionName;
    this.listOfCountry = listOfCountry;
    this.listOfDepartment = listOfDepartment;
  }

  @Override
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regionSequenceGenerator")
  @GenericGenerator(name = "regionSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
            @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "region_seq"),
            @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
            @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
  })
  public String getBaseId() {
    return super.getBaseId();
  }

  /**
   * Gets region code.
   *
   * @return the region code
   */
  public String getRegionCode() {
    return regionCode;
  }

  /**
   * Sets region code.
   *
   * @param regionCode the region code
   */
  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }

  /**
   * Gets region name.
   *
   * @return the region name
   */
  public String getRegionName() {
    return regionName;
  }

  /**
   * Sets region name.
   *
   * @param regionName the region name
   */
  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  /**
   * Gets list of country.
   *
   * @return the list of country
   */
  public Collection<Country> getListOfCountry() {
    return listOfCountry;
  }

  /**
   * Sets list of country.
   *
   * @param listOfCountry the list of country
   */
  public void setListOfCountry(Collection<Country> listOfCountry) {
    this.listOfCountry = listOfCountry;
  }

  /**
   * Gets list of department.
   *
   * @return the list of department
   */
  public Collection<Department> getListOfDepartment() {
    return listOfDepartment;
  }

  /**
   * Sets list of department.
   *
   * @param listOfDepartment the list of department
   */
  public void setListOfDepartment(Collection<Department> listOfDepartment) {
    this.listOfDepartment = listOfDepartment;
  }

  /**
   * Add country.
   *
   * @param country the country
   */
  public void addCountry(Country country) {
    listOfCountry.add(country);
    country.setRegionCode(this);

  }

  /**
   * Remove country.
   *
   * @param country the country
   */
  public void removeCountry(Country country) {
    listOfCountry.remove(country);
    country.setRegionCode(null);
  }

  /**
   * Add department.
   *
   * @param department the department
   */
  public void addDepartment(Department department) {
    listOfDepartment.add(department);
    department.setRegionCode(this);

  }

  /**
   * Remove department.
   *
   * @param department the department
   */
  public void removeDepartment(Department department) {
    listOfDepartment.remove(department);
    department.setRegionCode(null);
  }
}
