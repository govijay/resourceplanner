package com.sap.ariba.cts.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ariba.cts.model.dto.ProjectStaffingDto;
import com.sap.ariba.cts.model.entity.ProjectStaffing;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.service.impl.ProjectStaffingServiceImpl;
import com.sap.ariba.cts.service.impl.RegionServiceImpl;
import com.sap.ariba.cts.utils.Constants;

import java.util.List;

@RestController
@RequestMapping(path = Constants.USR_DETAILS_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ProjectStaffingController {

  private static Logger logger = LoggerFactory.getLogger(ProjectStaffingController.class);

  @Autowired
  private ProjectStaffingServiceImpl projStaffService;

  @Autowired
  private RegionServiceImpl regionService;


  @PostMapping(path = Constants.PROJECT_STAFFINGS_URL)
  public ResponseEntity<ProjectStaffingDto> createProjectStaffing(@Valid @RequestBody ProjectStaffingDto projectStaffingDto) {
    ProjectStaffing projStaffCreated = projStaffService.createProjectStaffing(ProjectStaffing.toEntity(projectStaffingDto));
    return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaffCreated), HttpStatus.OK);
  }

  @PutMapping(path = Constants.PROJECT_STAFFINGS_URL)
  public ResponseEntity<ProjectStaffingDto> updateProjectStaffing(@Valid @RequestBody ProjectStaffingDto projectStaffingDto) {

    if (projStaffService.isProjectStaffingExists(projectStaffingDto.getBaseId())) {
      ProjectStaffing projStaffUpdated = projStaffService.updateProjectStaffing(ProjectStaffing.toEntity(projectStaffingDto));
      return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaffUpdated), HttpStatus.OK);
    } else {
      return new ResponseEntity("PROJECT STAFF NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }


  @PatchMapping(path = Constants.PROJECT_STAFFINGS_STATUS_SET_URL)
  public ResponseEntity<ProjectStaffingDto> setStatusProjectStaffing(@PathVariable(name = "projStaffBaseId", required = true) @NotBlank String projStaffBaseId,
                                                           @PathVariable(name = "flag", required = true) boolean flag) {
    if (projStaffService.isProjectStaffingExists(projStaffBaseId)) {
      ProjectStaffing projStaffStatusUpd = null;
      if (!flag) {
        projStaffStatusUpd = projStaffService.deactivateProjectStaffing(projStaffBaseId);
        return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaffStatusUpd), HttpStatus.OK);
      } else {
        projStaffStatusUpd = projStaffService.reactivateProjectStaffing(projStaffBaseId);
        return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaffStatusUpd), HttpStatus.OK);
      }
    }
    return new ResponseEntity("PROJECT STAFF  NOT FOUND", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping(path = Constants.PROJECT_STAFFINGS_CODE_URL)
  public ResponseEntity<Boolean> deleteProjectStaffing(@PathVariable(name = "projStaffBaseId", required = true) @NotBlank String projStaffBaseId) {
    if (projStaffService.isProjectStaffingExists(projStaffBaseId)) {
      return new ResponseEntity<>(projStaffService.deleteProjectStaffing(projStaffBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("PROJECT STAFF  NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.PROJECT_STAFFINGS_CODE_URL)
  public ResponseEntity<ProjectStaffingDto> getProjectStaffingByBaseId(@PathVariable(name = "projStaffBaseId", required = true) @NotBlank String projStaffBaseId) {

    ProjectStaffing projStaff = projStaffService.getProjectStaffingByBaseId(projStaffBaseId);
    if (projStaff != null) {
      return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaff), HttpStatus.OK);
    } else {
      return new ResponseEntity("PROJECT STAFF  NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }


  @GetMapping(path = Constants.PROJECT_STAFFINGS_STATUS_GET_URL)
  public ResponseEntity<Boolean> isProjectStaffingActive(@PathVariable(name = "projStaffBaseId", required = true) @NotBlank String projStaffBaseId) {
    if (projStaffService.isProjectStaffingExists(projStaffBaseId)) {
      return new ResponseEntity<>(projStaffService.isProjectStaffingActive(projStaffBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("PROJECT STAFF  NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }


  @GetMapping(path = Constants.PROJECT_STAFFINGS_BY_CUS_URL)
  public ResponseEntity<List<ProjectStaffingDto>> getProjectStaffingsByCustomerName(@PathVariable(name = "cusName", required = true) @NotBlank String cusName) {
    return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaffService.getProjectStaffingByCustomerName(cusName)), HttpStatus.OK);
  }

  @GetMapping(path = Constants.PROJECT_STAFFINGS_BY_REG_URL)
  public ResponseEntity<List<ProjectStaffingDto>> getProjectStaffingsByRegion(@PathVariable(name = "regBaseId", required = true) @NotBlank String regBaseId) {
    Region region = regionService.getRegionByBaseId(regBaseId);
    return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaffService.getProjectStaffingByRegion(region)), HttpStatus.OK);
  }

  @GetMapping(path = Constants.PROJECT_STAFFINGS_URL)
  public ResponseEntity<List<ProjectStaffingDto>> getProjectStaffings() {
    return new ResponseEntity<>(ProjectStaffingDto.toDto(projStaffService.getProjectStaffing()), HttpStatus.OK);
  }


  @GetMapping(path = Constants.PROJECT_STAFFINGS_COUNT_URL)
  public ResponseEntity getProjectStaffingsCount() {
    return new ResponseEntity<>(projStaffService.countProjectStaffing(), HttpStatus.OK);
  }
}
