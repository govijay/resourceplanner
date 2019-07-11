package com.sap.ariba.cts.controller;


import com.sap.ariba.cts.model.dto.SubTeamDto;
import com.sap.ariba.cts.model.dto.TeamDto;
import com.sap.ariba.cts.model.entity.SubTeam;
import com.sap.ariba.cts.model.entity.Team;
import com.sap.ariba.cts.service.impl.SubTeamServiceImpl;
import com.sap.ariba.cts.service.impl.TeamServiceImpl;
import com.sap.ariba.cts.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SubTeamController {

    private static Logger logger = LoggerFactory.getLogger(SubTeamController.class);

    @Autowired
    SubTeamServiceImpl subTeamService;

    @Autowired
    TeamServiceImpl teamService;

    @PostMapping(path = Constants.SUB_TEAM_URL)
    public ResponseEntity<SubTeamDto> createSubTeam(@Valid @RequestBody SubTeamDto subTeamDto) {
        return new ResponseEntity(SubTeamDto.toDto(subTeamService.createSubTeam(SubTeam.toEntity(subTeamDto))), HttpStatus.OK);
    }

    @PutMapping(path = Constants.SUB_TEAM_URL)
    public ResponseEntity<SubTeamDto> updateSubTeam(@Valid @RequestBody SubTeamDto subTeamDto) {
        return new ResponseEntity(SubTeamDto.toDto(subTeamService.updateSubTeam(SubTeam.toEntity(subTeamDto))), HttpStatus.OK);
    }

    @PatchMapping(path = Constants.SUB_TEAM_STATUS_SET_URL)
    public ResponseEntity<SubTeamDto> setStatusSubTeam(@PathVariable(name = "subTeamBaseId", required = true) @NotBlank String subTeamBaseId,
                                                       @PathVariable(name = "flag", required = true) boolean flag) {
        if (subTeamService.isSubTeamExists(subTeamBaseId)) {
            SubTeam subTeamStatusUpd = null;
            if (!flag) {
                subTeamStatusUpd = subTeamService.deactivateSubTeam(subTeamBaseId);
                return new ResponseEntity<>(SubTeamDto.toDto(subTeamStatusUpd), HttpStatus.OK);
            } else {
                subTeamStatusUpd = subTeamService.reactivateSubTeam(subTeamBaseId);
                return new ResponseEntity<>(SubTeamDto.toDto(subTeamStatusUpd), HttpStatus.OK);
            }
        }
        return new ResponseEntity("SUB TEAM NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = Constants.SUB_TEAM_CODE_URL)
    public ResponseEntity<Boolean> deleteSubTeam(@PathVariable(name = "subTeamBaseId", required = true) @NotBlank String subTeamBaseId) {
        if (subTeamService.isSubTeamExists(subTeamBaseId)) {
            return new ResponseEntity<>(subTeamService.deleteSubTeam(subTeamBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("SUB TEAM NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.SUB_TEAM_CODE_URL)
    public ResponseEntity<SubTeamDto> getSubTeamByBaseId(@PathVariable(name = "subTeamBaseId", required = true) @NotBlank String subTeamBaseId) {

        SubTeam subTeam = subTeamService.getSubTeamByBaseId(subTeamBaseId);
        if (subTeam != null) {
            return new ResponseEntity<>(SubTeamDto.toDto(subTeam), HttpStatus.OK);
        } else {
            return new ResponseEntity("SUB TEAM NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.SUB_TEAM_STATUS_GET_URL)
    public ResponseEntity<Boolean> isSubTeamActive(@PathVariable(name = "subTeamBaseId", required = true) @NotBlank String subTeamBaseId) {
        if (subTeamService.isSubTeamExists(subTeamBaseId)) {
            return new ResponseEntity<>(subTeamService.isSubTeamActive(subTeamBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("SUB TEAM NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.SUB_TEAM_URL)
    public ResponseEntity<List<SubTeamDto>> getSubTeams() {
        return new ResponseEntity<>(SubTeamDto.toDto(subTeamService.getSubTeams()), HttpStatus.OK);
    }

    @GetMapping(path = Constants.SUB_TEAM_TEAM_GET_URL)
    public ResponseEntity<TeamDto> getTeamBySubTeamBaseID(@PathVariable(name = "subTeamBaseId", required = true) @NotBlank String subTeamBaseId) {
        if (subTeamService.isSubTeamExists(subTeamBaseId)) {
            Team team = subTeamService.getTeamBySubBaseId(subTeamBaseId);
            return new ResponseEntity<>(TeamDto.toDto(team), HttpStatus.OK);
        } else {
            return new ResponseEntity("SUB TEAM NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.SUB_TEAMS_TEAM_GET_URL)
    public ResponseEntity<List<SubTeamDto>> getSubTeamsByTeamBaseId(@PathVariable(name = "teamBaseId", required = true) @NotBlank String teamBaseId) {
        if (teamService.isTeamExists(teamBaseId)) {
            return new ResponseEntity<>(SubTeamDto.toDto(subTeamService.getSubTeamsByTeamBaseId(teamBaseId)), HttpStatus.OK);
        } else {
            return new ResponseEntity("TEAM NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.SUB_TEAM_COUNT_URL)
    public ResponseEntity getSubTeamsCount() {
        return new ResponseEntity<>(subTeamService.countSubTeams(), HttpStatus.OK);
    }


}
