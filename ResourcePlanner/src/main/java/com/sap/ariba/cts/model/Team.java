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

import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

import java.util.ArrayList;
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
@Table(name = "TEAM")
@ClassMetaProperty(code = "TM")
public class Team extends BaseEntity {

  @Column(name = "TEAM_CODE",unique = true)
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

  /**
   * Instantiates a new Team.
   */
  public Team() {
  }

  /**
   * Instantiates a new Team.
   *
   * @param teamCode   the team code
   * @param teamName   the team name
   * @param department the department
   */
  public Team(String teamCode, String teamName, Department department) {
    this.teamCode = teamCode;
    this.teamName = teamName;
    this.department = department;
  }

  @Override
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamSequenceGenerator")
  @GenericGenerator(name = "teamSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "team_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  public String getBaseId() {
    return super.getBaseId();
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

  /**
   * Gets list of sub team.
   *
   * @return the list of sub team
   */
  public Collection<SubTeam> getListOfSubTeam() {
    return listOfSubTeam;
  }

  /**
   * Sets list of sub team.
   *
   * @param listOfSubTeam the list of sub team
   */
  public void setListOfSubTeam(Collection<SubTeam> listOfSubTeam) {
    this.listOfSubTeam = listOfSubTeam;
  }

  /**
   * Add sub team.
   *
   * @param subTeam the sub team
   */
  public void addSubTeam(SubTeam subTeam) {
    listOfSubTeam.add(subTeam);
    subTeam.setTeam(this);

  }

  /**
   * Remove sub team.
   *
   * @param subTeam the sub team
   */
  public void removeSubTeam(SubTeam subTeam) {
    listOfSubTeam.remove(subTeam);
    subTeam.setTeam(null);
  }
}
