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
 * Team Model.
 *
 * <p> Team Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "TEAMS")
public class Team extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamSequenceGenerator")
  @GenericGenerator(name = "teamSequenceGenerator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {
                  @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "team_seq"),
                  @Parameter(name = SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, value = "_TM"),
                  @Parameter(name = SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, value = "true"),
                  @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "TEAM_CODE",unique = true)
  @NotBlank
  private String teamCode;

  @Column(name = "TEAM_NAME")
  @NotBlank
  private String teamName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DEPART_BASEID")
  @JsonBackReference
  private Department departmentBaseId;

  @OneToMany(mappedBy = "teamBaseId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonManagedReference
  private Collection<SubTeam> subTeams;

  /**
   * Instantiates a new Team.
   */
  protected Team() {
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
    return departmentBaseId;
  }

  /**
   * Sets department.
   *
   * @param department the department
   */
  public void setDepartment(Department department) {
    this.departmentBaseId = department;
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
            ", departmentBaseId=" + departmentBaseId +
            '}';
  }
}
