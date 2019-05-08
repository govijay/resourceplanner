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
 * Team Model.
 *
 * <p> Team Entity Class</p>
 *
 * @author Vijay.G
 * @version 1.0
 */

@Entity
@Table(name = "TEAM")
@SequenceGenerator(name = "default_gen", sequenceName = "team_seq", allocationSize = 1)
public class Team extends BaseEntity {

  @Column(name = "TEAM_CODE")
  @NotBlank
  private String teamCode;

  @Column(name = "TEAM_NAME")
  @NotBlank
  private String teamName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DEPART_CODE", nullable = false)
  private Department department;

  @OneToMany(mappedBy = "team")
  private Collection<SubTeam> listOfSubTeam = new ArrayList<>();

  public Team() {
  }

  public Team(String teamCode, String teamName, Department department) {
    this.teamCode = teamCode;
    this.teamName = teamName;
    this.department = department;
  }

  public String getTeamCode() {
    return teamCode;
  }

  public void setTeamCode(String teamCode) {
    this.teamCode = teamCode;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Collection<SubTeam> getListOfSubTeam() {
    return listOfSubTeam;
  }

  public void setListOfSubTeam(Collection<SubTeam> listOfSubTeam) {
    this.listOfSubTeam = listOfSubTeam;
  }

  public void addSubTeam(SubTeam subTeam) {
    listOfSubTeam.add(subTeam);
    subTeam.setTeam(this);

  }

  public void removeSubTeam(SubTeam subTeam) {
    listOfSubTeam.remove(subTeam);
    subTeam.setTeam(null);
  }
}
