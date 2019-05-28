package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.SubTeamDto;
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
  private String subTeamCode;

  @Column(name = "SUB_TEAM_NAME")
  private String subTeamName;

  @ManyToOne
  @JoinColumn(name = "TEAM_BASEID")
  @JsonBackReference
  private Team team;

  @Transient
  private String teamBaseId;


  /**
   * Instantiates a new Sub team.
   */
  protected SubTeam() {
  }

  /**
   * Instantiates a new Base entity.
   *
   * @param active the active
   */
  public SubTeam(String baseId, boolean active, String subTeamCode, String subTeamName, String teamBaseId) {
    super(active);
    this.baseId = baseId;
    this.subTeamCode = subTeamCode;
    this.subTeamName = subTeamName;
    this.teamBaseId = teamBaseId;
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
  public Team getTeam() {
    return team;
  }

  /**
   * Sets team base id.
   *
   * @param team the team base id
   */
  public void setTeam(Team team) {
    this.team = team;
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

  public String getTeamBaseId() {
    return teamBaseId;
  }

  public void setTeamBaseId(String teamBaseId) {
    this.teamBaseId = teamBaseId;
  }

  public static SubTeam toEntity(SubTeamDto subTeamDto) {
    SubTeam subTeam = new SubTeam(
            subTeamDto.getBaseId(),
            subTeamDto.isActive(),
            subTeamDto.getSubTeamCode(),
            subTeamDto.getSubTeamName(),
            subTeamDto.getTeamBaseId()
    );
    return subTeam;
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
