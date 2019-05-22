package com.sap.ariba.cts.model.dto;

import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.SubTeam;

import java.util.ArrayList;
import java.util.List;

public class SubTeamDto extends BaseDto {

  String subTeamCode;
  String subTeamName;

  public SubTeamDto() {
  }

  public SubTeamDto(String baseId, boolean isActive, String subTeamCode, String subTeamName) {
    super(baseId, isActive);
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

  public static SubTeamDto toDto(SubTeam subTeam) {
    return new SubTeamDto(subTeam.getBaseId(),
            subTeam.isActive(),
            subTeam.getSubTeamCode(),
            subTeam.getSubTeamName());
  }

  public static List<SubTeamDto> toDto(List<SubTeam> subTeams) {
    List<SubTeamDto> subTeamDtos = new ArrayList<>();

    for (SubTeam subTeam : subTeams) {
      subTeamDtos.add(SubTeamDto.toDto(subTeam));
    }

    return subTeamDtos;
  }
}
