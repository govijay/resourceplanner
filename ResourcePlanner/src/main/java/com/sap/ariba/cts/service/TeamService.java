package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Team;

import java.util.List;

public interface TeamService {

  Long countTeams();

  Team createTeam(Team team);

  Team updateTeam(Team team);

  Team deactivateTeam(String baseId);

  Team reactivateTeam(String baseId);

  Boolean deleteTeam(String baseId);

  Boolean isTeamActive(String baseId);

  Boolean isTeamExists(String baseId);

  Team getTeamByBaseId(String baseId);

  Department getDepartmentByTeamBaseId(String baseId);



  List<Team> getTeams();

  List<Team> getTeamsByDepartmentBaseId(String deptBaseId);



}
