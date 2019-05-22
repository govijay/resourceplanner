package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseEntity;
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
@Table(name = "SUB_TEAMS")
@ClassMetaProperty(code = "ST")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubTeam extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subteamSequenceGenerator")
  @GenericGenerator(name = "subteamSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "subteam_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "SUB_TEAM_CODE")
  @NotBlank
  private String subTeamCode;

  @Column(name = "SUB_TEAM_NAME")
  @NotBlank
  private String subTeamName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_BASEID")
  @JsonBackReference
  private Team teamBaseId;


  /**
   * Instantiates a new Sub team.
   */
  protected SubTeam() {
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
   * Gets team base id.
   *
   * @return the team base id
   */
  public Team getTeamBaseId() {
    return teamBaseId;
  }

  /**
   * Sets team base id.
   *
   * @param teamBaseId the team base id
   */
  public void setTeamBaseId(Team teamBaseId) {
    this.teamBaseId = teamBaseId;
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

  @Override
  public String toString() {
    return "SubTeam{" +
            "baseId='" + baseId + '\'' +
            ", subTeamCode='" + subTeamCode + '\'' +
            ", subTeamName='" + subTeamName + '\'' +
            ", teamBaseId=" + teamBaseId +
            '}';
  }
}
