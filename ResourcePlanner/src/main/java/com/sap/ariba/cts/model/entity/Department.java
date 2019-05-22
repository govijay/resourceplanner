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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

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
@Table(name = "DEPARTMENTS")
@ClassMetaProperty(code = "DEPT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deptSequenceGenerator")
  @GenericGenerator(name = "deptSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "dept_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "DEPART_CODE")
  @NotBlank
  private String departCode;

  @Column(name = "DEPART_NAME")
  @NotBlank
  private String departName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "REGION_BASEID", nullable = true)
  @JsonBackReference
  private Region regionBaseId;

  @OneToMany(mappedBy = "departmentBaseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonManagedReference
  private Collection<Team> teams;

  /**
   * Instantiates a new Department.
   */
  protected Department() {
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
   * Gets region base id.
   *
   * @return the region base id
   */
  public Region getRegionBaseId() {
    return regionBaseId;
  }

  /**
   * Sets region base id.
   *
   * @param regionBaseId the region base id
   */
  public void setRegionBaseId(Region regionBaseId) {
    this.regionBaseId = regionBaseId;
  }

  /**
   * Gets teams.
   *
   * @return the teams
   */
  public Collection<Team> getTeams() {
    return teams;
  }

  /**
   * Sets teams.
   *
   * @param teams the teams
   */
  public void setTeams(Collection<Team> teams) {
    this.teams = teams;
  }

  /**
   * Pre persist.
   */
  @PrePersist
  public void prePersist() {
    this.setActive(true);
  }

  @Override
  public String toString() {
    return "Department{" +
            "baseId='" + baseId + '\'' +
            ", departCode='" + departCode + '\'' +
            ", departName='" + departName + '\'' +
            '}';
  }
}
