package com.sap.ariba.cts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Region Model.
 *
 * <p> Region Entity Class</p>
 *
 * @author Vijay.G
 * @version 1.0
 */

@Entity
@Table(name = "REGION")
@SequenceGenerator(name = "default_gen", sequenceName = "region_seq", allocationSize = 1)
public class Region extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;


  @Column(name = "REGION_CODE")
  @NotBlank
  private String regionCode;

  @Column(name = "REGION_NAME")
  @NotBlank
  private String regionName;

  @OneToMany(mappedBy = "regionCode")
  private Collection<Country> listOfCountry = new ArrayList<>();

  @OneToMany(mappedBy = "regionCode")
  private Collection<Department> listOfDepartment = new ArrayList<>();

  public Region() {
  }

  public Region(@NotBlank String regionCode, @NotBlank String regionName) {
    this.regionCode = regionCode;
    this.regionName = regionName;
  }

  public Region(@NotBlank String regionCode, @NotBlank String regionName, Collection<Country> listOfCountry, Collection<Department> listOfDepartment) {
    this.regionCode = regionCode;
    this.regionName = regionName;
    this.listOfCountry = listOfCountry;
    this.listOfDepartment = listOfDepartment;
  }

  public String getRegionCode() {
    return regionCode;
  }

  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public Collection<Country> getListOfCountry() {
    return listOfCountry;
  }

  public void setListOfCountry(Collection<Country> listOfCountry) {
    this.listOfCountry = listOfCountry;
  }

  public Collection<Department> getListOfDepartment() {
    return listOfDepartment;
  }

  public void setListOfDepartment(Collection<Department> listOfDepartment) {
    this.listOfDepartment = listOfDepartment;
  }

  public void addCountry(Country country) {
    listOfCountry.add(country);
    country.setRegionCode(this);

  }

  public void removeCountry(Country country) {
    listOfCountry.remove(country);
    country.setRegionCode(null);
  }

  public void addDepartment(Department department) {
    listOfDepartment.add(department);
    department.setRegionCode(this);

  }

  public void removeDepartment(Department department) {
    listOfDepartment.remove(department);
    department.setRegionCode(null);
  }
}
