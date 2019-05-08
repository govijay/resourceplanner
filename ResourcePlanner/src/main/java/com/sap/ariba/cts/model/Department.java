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
 * Department Model.
 *
 * <p> Department Entity Class.</p>
 *
 * @author Vijay.G
 * @version 1.0
 */

@Entity
@Table(name = "DEPARTMENT")
@SequenceGenerator(name = "default_gen", sequenceName = "department_seq", allocationSize = 1)
public class Department extends BaseEntity {

  @Column(name = "DEPART_CODE")
  @NotBlank
  private String departCode;

  @Column(name = "DEPART_NAME")
  @NotBlank
  private String departName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "REGION_CODE", nullable = false)
  private Region regionCode;

  @OneToMany(mappedBy = "department")
  private Collection<Team> listOfTeam = new ArrayList<>();

  public Department() {
  }

  public Department(String departCode, String departName, Region regionCode) {
    this.departCode = departCode;
    this.departName = departName;
    this.regionCode = regionCode;
  }

  public String getDepartCode() {
    return departCode;
  }

  public void setDepartCode(String departCode) {
    this.departCode = departCode;
  }

  public String getDepartName() {
    return departName;
  }

  public void setDepartName(String departName) {
    this.departName = departName;
  }

  public Region getRegionCode() {
    return regionCode;
  }

  public void setRegionCode(Region regionCode) {
    this.regionCode = regionCode;
  }

  public Collection<Team> getListOfTeam() {
    return listOfTeam;
  }

  public void setListOfTeam(Collection<Team> listOfTeam) {
    this.listOfTeam = listOfTeam;
  }

  public void addTeam(Team team) {
    listOfTeam.add(team);
    team.setDepartment(this);

  }

  public void removeTeam(Team team) {
    listOfTeam.remove(team);
    team.setDepartment(null);
  }
}
