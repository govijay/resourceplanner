package com.sap.ariba.cts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.SubTeam;
import com.sap.ariba.cts.model.entity.Team;
import com.sap.ariba.cts.repository.DepartmentRepository;
import com.sap.ariba.cts.repository.TeamRepository;
import com.sap.ariba.cts.service.TeamService;
import com.sap.ariba.cts.utils.GenericUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

  private static Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

  @Autowired
  private TeamRepository teamRepo;

  @Autowired
  private DepartmentRepository deptRepo;

  @Override
  public Long countTeams() {
    return teamRepo.count();
  }

  @Override
  public Team createTeam(Team team) {

    String deptId = team.getDepartmentBaseId();
    if (deptId != null) {
      logger.info(deptId);
      Department deptFromDB = deptRepo.getDepartmentByBaseId(deptId);
      if (deptFromDB != null) {
        team.setDepartment(deptFromDB);
      }
    }
    teamRepo.save(team);
    return (Team) teamRepo.findById(team.getBaseId()).get();

  }

  @Override
  public Team updateTeam(Team team) {
    Department deptFromDb = null;
    String deptBaseId = team.getDepartmentBaseId();
    if(deptBaseId != null){
      deptFromDb = deptRepo.getDepartmentByBaseId(deptBaseId);
    }

    Team teamToUpdate = teamRepo.getTeamByBaseId(team.getBaseId());

    if(teamToUpdate != null){
      GenericUtil.copyNonNullProperties(team, teamToUpdate);
      if(deptFromDb != null) teamToUpdate.setDepartment(deptFromDb);
      teamRepo.save(teamToUpdate);
    }
    return teamToUpdate;
  }

  @Override
  public Team deactivateTeam(String baseId) {
    Team teamToDeactivate = null;
    if(baseId != null){
      teamToDeactivate = teamRepo.getTeamByBaseId(baseId);
    }
    if(teamToDeactivate != null && teamToDeactivate.isActive()){
      teamToDeactivate.setActive(false);
      teamRepo.save(teamToDeactivate);
    }
    return teamToDeactivate;
  }

  @Override
  public Team reactivateTeam(String baseId) {
    Team teamToReactivate = null;
    if(baseId != null){
      teamToReactivate = teamRepo.getTeamByBaseId(baseId);
    }
    if(teamToReactivate != null && !teamToReactivate.isActive()){
      teamToReactivate.setActive(true);
      teamRepo.save(teamToReactivate);
    }
    return  teamToReactivate;
  }

  @Override
  public Boolean deleteTeam(String baseId) {
    Team teamToHardDelete = null;
    if(baseId != null){
      teamToHardDelete = teamRepo.getTeamByBaseId(baseId);
    }
    if (teamToHardDelete != null) {
      teamRepo.delete(teamToHardDelete);
    }
    if (teamToHardDelete != null) {
      Collection<SubTeam> subTeams = (Collection<SubTeam>) teamToHardDelete.getSubTeams();
      if (!subTeams.isEmpty()) {
        for (SubTeam subTeam : subTeams) {
          subTeam.setTeam(null);
        }
      }
    }

    return true;
  }

  @Override
  public Boolean isTeamActive(String baseId) {
    Team team = teamRepo.getTeamByBaseId(baseId);
    if(team != null){
      return team.isActive();
    }
    return false;
  }

  @Override
  public Boolean isTeamExists(String baseId) {
    return teamRepo.existsById(baseId);
  }

  @Override
  public Team getTeamByBaseId(String baseId) {
    return teamRepo.getTeamByBaseId(baseId);
  }


  @Override
  public List<Team> getTeams() {
    return teamRepo.getActiveEntity();
  }

  @Override
  public List<Team> getTeamsByDepartmentBaseId(String deptBaseId) {

    List<Team> teams = new ArrayList<>();

    Department department = deptRepo.getDepartmentByBaseId(deptBaseId);

    if (department != null) {
      teams = (List<Team>) teamRepo.getTeamsByDepartment(department);
    }
    return teams;
  }

  @Override
  public Department getDepartmentByTeamBaseId(String baseId) {
    Department dept = null;
    Team team = teamRepo.getTeamByBaseId(baseId);
    if (team != null) {
      dept = team.getDepartment();
    }
    return dept;
  }
}
