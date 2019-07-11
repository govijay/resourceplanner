package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.SubTeam;
import com.sap.ariba.cts.model.entity.Team;

import java.util.List;

public interface SubTeamService {
    Long countSubTeams();

    SubTeam createSubTeam(SubTeam subTeam);

    SubTeam updateSubTeam(SubTeam subTeam);

    SubTeam deactivateSubTeam(String baseId);

    SubTeam reactivateSubTeam(String baseId);

    Boolean deleteSubTeam(String baseId);

    Boolean isSubTeamActive(String baseId);

    Boolean isSubTeamExists(String baseId);

    SubTeam getSubTeamByBaseId(String baseId);

    Team getTeamBySubBaseId(String baseId);

    List<SubTeam> getSubTeams();

    List<SubTeam> getSubTeamsByTeamBaseId(String teamBaseId);
}
