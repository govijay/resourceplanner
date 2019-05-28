package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.TeamDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

import java.util.Collection;

/**
 * Team Model.
 *
 * <p> Team Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "TEAMS")
@ClassMetaProperty(code = "TM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamSequenceGenerator")
  @GenericGenerator(name = "teamSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "team_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "TEAM_CODE",unique = true)
  private String teamCode;

  @Column(name = "TEAM_NAME")
  private String teamName;

  @ManyToOne
  @JoinColumn(name = "DEPART_BASEID")
  @JsonBackReference
  private Department department;

  @Transient
  private String departmentBaseId;

  @OneToMany(mappedBy = "team")
  @JsonManagedReference
  private Collection<SubTeam> subTeams;

  /**
   * Instantiates a new Team.
   */
  protected Team() {
  }

  /**
   * Instantiates a new Base entity.
   *
   * @param active the active
   */
  public Team(String baseId, boolean active, String teamCode, String teamName, String departmentBaseId) {
    super(active);
    this.baseId = baseId;
    this.teamCode = teamCode;
    this.teamName = teamName;
    this.departmentBaseId = departmentBaseId;

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
   * Gets team code.
   *
   * @return the team code
   */
  public String getTeamCode() {
    return teamCode;
  }

  /**
   * Sets team code.
   *
   * @param teamCode the team code
   */
  public void setTeamCode(String teamCode) {
    this.teamCode = teamCode;
  }

  /**
   * Gets team name.
   *
   * @return the team name
   */
  public String getTeamName() {
    return teamName;
  }

  /**
   * Sets team name.
   *
   * @param teamName the team name
   */
  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  /**
   * Gets department.
   *
   * @return the department
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * Sets department.
   *
   * @param department the department
   */
  public void setDepartment(Department department) {
    this.department = department;
  }

  public String getDepartmentBaseId() {
    return departmentBaseId;
  }

  public void setDepartmentBaseId(String departmentBaseId) {
    this.departmentBaseId = departmentBaseId;
  }

  /**
   * Gets sub teams.
   *
   * @return the sub teams
   */
  public Collection<SubTeam> getSubTeams() {
    return subTeams;
  }

  /**
   * Sets sub teams.
   *
   * @param subTeams the sub teams
   */
  public void setSubTeams(Collection<SubTeam> subTeams) {
    this.subTeams = subTeams;
  }

  @Override
  public String toString() {
    return "Team{" +
            "baseId='" + baseId + '\'' +
            ", teamCode='" + teamCode + '\'' +
            ", teamName='" + teamName + '\'' +
            ", department=" + department +
            '}';
  }

  public static Team toEntity(TeamDto teamDto){
      Team team = new Team(
              teamDto.getBaseId(),
              teamDto.isActive(),
              teamDto.getTeamCode(),
              teamDto.getTeamName(),
              teamDto.getDepartmentBaseId()
      );
      return team;
  }

}
