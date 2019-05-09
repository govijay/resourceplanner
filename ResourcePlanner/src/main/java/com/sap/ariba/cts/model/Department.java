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
 * Department Model.
 *
 * <p> Department Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "DEPARTMENT")
@ClassMetaProperty(code = "DEP")
public class Department extends BaseEntity {

  @Column(name = "DEPART_CODE",unique = true)
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

  /**
   * Instantiates a new Department.
   */
  public Department() {
  }

  /**
   * Instantiates a new Department.
   *
   * @param departCode the depart code
   * @param departName the depart name
   * @param regionCode the region code
   */
  public Department(String departCode, String departName, Region regionCode) {
    this.departCode = departCode;
    this.departName = departName;
    this.regionCode = regionCode;
  }

  @Override
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deptSequenceGenerator")
  @GenericGenerator(name = "deptSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "dept_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  public String getBaseId() {
    return super.getBaseId();
  }

  /**
   * Gets depart code.
   *
   * @return the depart code
   */
  public String getDepartCode() {
    return departCode;
  }

  /**
   * Sets depart code.
   *
   * @param departCode the depart code
   */
  public void setDepartCode(String departCode) {
    this.departCode = departCode;
  }

  /**
   * Gets depart name.
   *
   * @return the depart name
   */
  public String getDepartName() {
    return departName;
  }

  /**
   * Sets depart name.
   *
   * @param departName the depart name
   */
  public void setDepartName(String departName) {
    this.departName = departName;
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
   * Gets list of team.
   *
   * @return the list of team
   */
  public Collection<Team> getListOfTeam() {
    return listOfTeam;
  }

  /**
   * Sets list of team.
   *
   * @param listOfTeam the list of team
   */
  public void setListOfTeam(Collection<Team> listOfTeam) {
    this.listOfTeam = listOfTeam;
  }

  /**
   * Add team.
   *
   * @param team the team
   */
  public void addTeam(Team team) {
    listOfTeam.add(team);
    team.setDepartment(this);

  }

  /**
   * Remove team.
   *
   * @param team the team
   */
  public void removeTeam(Team team) {
    listOfTeam.remove(team);
    team.setDepartment(null);
  }
}
