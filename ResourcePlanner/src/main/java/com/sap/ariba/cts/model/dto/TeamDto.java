package com.sap.ariba.cts.model.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.Team;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto extends BaseDto {

  @NotEmpty
  @JsonProperty("code")
  String teamCode;

  @JsonProperty("name")
  String teamName;

  @JsonProperty("department")
  DepartmentDto departmentDto;

  @JsonProperty("departmentid")
  String departmentBaseId;

  @JsonProperty("subteams")
  List<SubTeamDto> subTeamDto;

   public TeamDto(String baseId, boolean isActive, String teamCode, String teamName ,String departmentBaseId ) {
    super(baseId, isActive);
    this.teamCode = teamCode;
    this.teamName = teamName;
    this.departmentBaseId = departmentBaseId;
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

  public String getDepartmentBaseId() {
    return departmentBaseId;
  }

  public void setDepartmentBaseId(String departmentBaseId) {
    this.departmentBaseId = departmentBaseId;
  }

  public List<SubTeamDto> getSubTeamDto() {
    return subTeamDto;
  }

  public void setSubTeamDto(List<SubTeamDto> subTeamDto) {
    this.subTeamDto = subTeamDto;
  }

  public static TeamDto toDto(Team team) {
    return new TeamDto(team.getBaseId(),
            team.isActive(),
            team.getTeamCode(),
            team.getTeamName(),
            team.getDepartmentBaseId());
  }

  public static List<TeamDto> toDto(List<Team> teams) {
    List<TeamDto> teamDtos = new ArrayList<>();

    for (Team team : teams) {
      teamDtos.add(TeamDto.toDto(team));
    }

    return teamDtos;
  }

}
