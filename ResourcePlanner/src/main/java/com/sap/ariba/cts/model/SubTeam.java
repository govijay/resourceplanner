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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

/**
 * Sub Team Model.
 *
 * <p> Sub Team Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "SUB_TEAM")
@ClassMetaProperty(code = "STM")
public class SubTeam extends BaseEntity {

  @Column(name = "SUB_TEAM_CODE",unique = true)
  @NotBlank
  private String subTeamCode;

  @Column(name = "SUB_TEAM_NAME")
  @NotBlank
  private String subTeamName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_CODE", nullable = false)
  private Team team;

  /**
   * Instantiates a new Sub team.
   */
  public SubTeam() {
  }

  /**
   * Instantiates a new Sub team.
   *
   * @param subTeamCode the sub team code
   * @param subTeamName the sub team name
   */
  public SubTeam(@NotBlank String subTeamCode, @NotBlank String subTeamName) {
    this.subTeamCode = subTeamCode;
    this.subTeamName = subTeamName;
  }

  @Override
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subTeamSequenceGenerator")
  @GenericGenerator(name = "subTeamSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "sub_team_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  public String getBaseId() {
    return super.getBaseId();
  }

  /**
   * Gets sub team code.
   *
   * @return the sub team code
   */
  public String getSubTeamCode() {
    return subTeamCode;
  }

  /**
   * Sets sub team code.
   *
   * @param subTeamCode the sub team code
   */
  public void setSubTeamCode(String subTeamCode) {
    this.subTeamCode = subTeamCode;
  }

  /**
   * Gets sub team name.
   *
   * @return the sub team name
   */
  public String getSubTeamName() {
    return subTeamName;
  }

  /**
   * Sets sub team name.
   *
   * @param subTeamName the sub team name
   */
  public void setSubTeamName(String subTeamName) {
    this.subTeamName = subTeamName;
  }

  /**
   * Gets team.
   *
   * @return the team
   */
  public Team getTeam() {
    return team;
  }

  /**
   * Sets team.
   *
   * @param team the team
   */
  public void setTeam(Team team) {
    this.team = team;
  }
}
