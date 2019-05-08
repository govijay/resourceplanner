package com.sap.ariba.cts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Sub Team Model.
 *
 * <p> Sub Team Entity Class.</p>
 *
 * @author Vijay.G
 * @version 1.0
 */

@Entity
@Table(name = "SUB_TEAM")
@SequenceGenerator(name = "default_gen", sequenceName = "subteam_seq", allocationSize = 1)
public class SubTeam extends BaseEntity {

  @Column(name = "SUB_TEAM_CODE")
  @NotBlank
  private String subTeamCode;

  @Column(name = "SUB_TEAM_NAME")
  @NotBlank
  private String subTeamName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_CODE", nullable = false)
  private Team team;

  public SubTeam() {
  }

  public SubTeam(@NotBlank String subTeamCode, @NotBlank String subTeamName) {
    this.subTeamCode = subTeamCode;
    this.subTeamName = subTeamName;
  }

  public String getSubTeamCode() {
    return subTeamCode;
  }

  public void setSubTeamCode(String subTeamCode) {
    this.subTeamCode = subTeamCode;
  }

  public String getSubTeamName() {
    return subTeamName;
  }

  public void setSubTeamName(String subTeamName) {
    this.subTeamName = subTeamName;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }
}
