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

import com.sap.ariba.cts.model.dto.JobRoleDto;
import com.sap.ariba.cts.model.entity.JobRole;
import com.sap.ariba.cts.service.impl.JobRoleServiceImpl;
import com.sap.ariba.cts.utils.Constants;

import java.util.List;

@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class JobRoleController {

  private static Logger logger = LoggerFactory.getLogger(JobRoleController.class);

  @Autowired
  JobRoleServiceImpl jobRoleService;

  @PostMapping(path = Constants.JOB_ROLE_URL)
  public ResponseEntity<JobRoleDto> createJobRole(@Valid @RequestBody JobRoleDto jobRoleDto) {
    JobRole jobRoleCreated = jobRoleService.createJobRole(JobRole.toEntity(jobRoleDto));
    return new ResponseEntity<>(JobRoleDto.toDto(jobRoleCreated), HttpStatus.OK);
  }

  @PutMapping(path = Constants.JOB_ROLE_URL)
  public ResponseEntity<JobRoleDto> updateJobRole(@Valid @RequestBody JobRoleDto jobRoleDto) {

    if (jobRoleService.isJobRoleExists(jobRoleDto.getBaseId())) {
      JobRole jobRoleUpdated = jobRoleService.updateJobRole(JobRole.toEntity(jobRoleDto));
      return new ResponseEntity<>(JobRoleDto.toDto(jobRoleUpdated), HttpStatus.OK);
    } else {
      return new ResponseEntity("JOB ROLE NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }


  @PatchMapping(path = Constants.JOB_ROLE_STATUS_SET_URL)
  public ResponseEntity<JobRoleDto> setStatusJobRole(@PathVariable(name = "jobRoleBaseId", required = true) @NotBlank String jobRoleBaseId,
                                                     @PathVariable(name = "flag", required = true) boolean flag) {
    if (jobRoleService.isJobRoleExists(jobRoleBaseId)) {
      JobRole jobRoleStatusUpd = null;
      if (!flag) {
        jobRoleStatusUpd = jobRoleService.deactivateJobRole(jobRoleBaseId);
        return new ResponseEntity<>(JobRoleDto.toDto(jobRoleStatusUpd), HttpStatus.OK);
      } else {
        jobRoleStatusUpd = jobRoleService.reactivateJobRole(jobRoleBaseId);
        return new ResponseEntity<>(JobRoleDto.toDto(jobRoleStatusUpd), HttpStatus.OK);
      }
    }
    return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping(path = Constants.JOB_ROLE_CODE_URL)
  public ResponseEntity<Boolean> deleteJobRole(@PathVariable(name = "jobRoleBaseId", required = true) @NotBlank String jobRoleBaseId) {
    if (jobRoleService.isJobRoleExists(jobRoleBaseId)) {
      return new ResponseEntity<>(jobRoleService.deleteJobRole(jobRoleBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("JOB ROLE NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.JOB_ROLE_CODE_URL)
  public ResponseEntity<JobRoleDto> getJobRoleByBaseId(@PathVariable(name = "jobRoleBaseId", required = true) @NotBlank String jobRoleBaseId) {

    JobRole jobRole = jobRoleService.getJobRoleByBaseId(jobRoleBaseId);
    if (jobRole != null) {
      return new ResponseEntity<>(JobRoleDto.toDto(jobRole), HttpStatus.OK);
    } else {
      return new ResponseEntity("JOB ROLE  NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }


  @GetMapping(path = Constants.JOB_ROLE_STATUS_GET_URL)
  public ResponseEntity<Boolean> isJobRoleActive(@PathVariable(name = "jobRoleBaseId", required = true) @NotBlank String jobRoleBaseId) {
    if (jobRoleService.isJobRoleExists(jobRoleBaseId)) {
      return new ResponseEntity<>(jobRoleService.isJobRoleActive(jobRoleBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("JOB ROLE NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.JOB_ROLE_URL)
  public ResponseEntity<List<JobRoleDto>> getJobRoles() {
    return new ResponseEntity<>(JobRoleDto.toDto(jobRoleService.getJobRoles()), HttpStatus.OK);
  }


  @GetMapping(path = Constants.JOB_ROLE_COUNT_URL)
  public ResponseEntity getJobRolesCount() {
    return new ResponseEntity<>(jobRoleService.countJobRoles(), HttpStatus.OK);
  }

}
