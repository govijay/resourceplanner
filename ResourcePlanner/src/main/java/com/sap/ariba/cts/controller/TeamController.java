package com.sap.ariba.cts.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ariba.cts.model.dto.DepartmentDto;
import com.sap.ariba.cts.model.dto.TeamDto;
import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Team;
import com.sap.ariba.cts.service.impl.DepartmentServiceImpl;
import com.sap.ariba.cts.service.impl.TeamServiceImpl;
import com.sap.ariba.cts.utils.Constants;

import java.util.List;

@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

  private static Logger logger = LoggerFactory.getLogger(TeamController.class);

  @Autowired
  TeamServiceImpl teamService;

  @Autowired
  DepartmentServiceImpl departmentService;

  @PostMapping(path = Constants.TEAM_URL)
  public ResponseEntity<TeamDto> createTeam(@Valid @RequestBody TeamDto team) {
      return new ResponseEntity(TeamDto.toDto(teamService.createTeam(Team.toEntity(team))), HttpStatus.OK);
  }

  @PutMapping(path = Constants.TEAM_URL)
  public ResponseEntity<TeamDto> updateTeam(@Valid @RequestBody TeamDto team) {
    return new ResponseEntity(TeamDto.toDto(teamService.updateTeam(Team.toEntity(team))), HttpStatus.OK);
  }

  @PatchMapping(path = Constants.TEAM_STATUS_SET_URL)
  public ResponseEntity<TeamDto> setStatusTeam(@PathVariable(name = "teamBaseId", required = true) @NotBlank String teamBaseId,
                                                           @PathVariable(name = "flag", required = true) boolean flag) {
    if (teamService.isTeamExists(teamBaseId)){
      Team teamStatusUpd = null;
      if (!flag) {
        teamStatusUpd = teamService.deactivateTeam(teamBaseId);
        return new ResponseEntity<>(TeamDto.toDto(teamStatusUpd), HttpStatus.OK);
      } else {
        teamStatusUpd = teamService.reactivateTeam(teamBaseId);
        return new ResponseEntity<>(TeamDto.toDto(teamStatusUpd), HttpStatus.OK);
      }
    }
    return new ResponseEntity("TEAM NOT FOUND", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping(path = Constants.TEAM_CODE_URL)
  public ResponseEntity<Boolean> deleteTeam(@PathVariable(name = "teamBaseId", required = true) @NotBlank String teamBaseId) {
    if (teamService.isTeamExists(teamBaseId)) {
      return new ResponseEntity<>(teamService.deleteTeam(teamBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("TEAM NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.TEAM_CODE_URL)
  public ResponseEntity<TeamDto> getTeamByBaseId(@PathVariable(name = "teamBaseId", required = true) @NotBlank String teamBaseId) {

    Team team = teamService.getTeamByBaseId(teamBaseId);
    if (team != null) {
      return new ResponseEntity<>(TeamDto.toDto(team), HttpStatus.OK);
    } else {
      return new ResponseEntity("TEAM NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.TEAM_STATUS_GET_URL)
  public ResponseEntity<Boolean> isTeamActive(@PathVariable(name = "teamBaseId", required = true) @NotBlank String teamBaseId) {
    if (teamService.isTeamExists(teamBaseId)) {
      return new ResponseEntity<>(teamService.isTeamActive(teamBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("TEAM NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.TEAM_URL)
  public ResponseEntity<List<TeamDto>> getTeams() {
    return new ResponseEntity<>(TeamDto.toDto(teamService.getTeams()), HttpStatus.OK);
  }

  @GetMapping(path = Constants.TEAM_DEPT_GET_URL)
  public ResponseEntity<DepartmentDto> getDepartmentByTeamBaseID(@PathVariable(name = "teamBaseId", required = true) @NotBlank String teamBaseId) {
    if (teamService.isTeamExists(teamBaseId)) {
      Department dept = teamService.getDepartmentByTeamBaseId(teamBaseId);
      return new ResponseEntity<>(DepartmentDto.toDto(dept), HttpStatus.OK);
    }else {
      return new ResponseEntity("TEAM NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.TEAMS_DEPT_GET_URL)
  public ResponseEntity<List<TeamDto>> getTeamsByDeptBaseId(@PathVariable(name = "deptBaseId", required = true) @NotBlank String deptBaseId) {
    if (departmentService.isDepartmentExists(deptBaseId)) {
      return new ResponseEntity<>(TeamDto.toDto(teamService.getTeamsByDepartmentBaseId(deptBaseId)), HttpStatus.OK);
    } else {
      return new ResponseEntity("DEPARTMENT NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.TEAM_COUNT_URL)
  public ResponseEntity getTeamsCount() {
    return new ResponseEntity<>(teamService.countTeams(), HttpStatus.OK);
  }





}
