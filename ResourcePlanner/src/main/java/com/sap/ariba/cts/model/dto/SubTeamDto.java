package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.SubTeam;
import com.sap.ariba.cts.model.entity.Team;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubTeamDto extends BaseDto {

    String subTeamCode;

    String subTeamName;

    TeamDto teamDto;

    String teamBaseId;

    public SubTeamDto() {
    }

    public SubTeamDto(String baseId, boolean isActive, String subTeamCode, String subTeamName) {
        super(baseId, isActive);
        this.subTeamCode = subTeamCode;
        this.subTeamName = subTeamName;
    }

    public static SubTeamDto toDto(SubTeam subTeam) {
        SubTeamDto subTeamDto = new SubTeamDto(subTeam.getBaseId(),
                subTeam.isActive(),
                subTeam.getSubTeamCode(),
                subTeam.getSubTeamName());

        Team team = subTeam.getTeam();
        if (team != null) {
            TeamDto teamDto = TeamDto.toDto(team);
            subTeamDto.setTeamDto(teamDto);
            subTeamDto.setTeamBaseId(team.getBaseId());
        }

        return subTeamDto;
    }

    public static List<SubTeamDto> toDto(List<SubTeam> subTeams) {
        List<SubTeamDto> subTeamDtos = new ArrayList<>();

        for (SubTeam subTeam : subTeams) {
            subTeamDtos.add(SubTeamDto.toDto(subTeam));
        }

        return subTeamDtos;
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

    public String getTeamBaseId() {
        return teamBaseId;
    }

    public void setTeamBaseId(String teamBaseId) {
        this.teamBaseId = teamBaseId;
    }

    public TeamDto getTeamDto() {
        return teamDto;
    }

    public void setTeamDto(TeamDto teamDto) {
        this.teamDto = teamDto;
    }
}
