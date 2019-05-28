package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.JobRole;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobRoleDto extends BaseDto {

  @JsonProperty("code")
  String jobRoleCode;

  @JsonProperty("name")
  String jobRoleName;

  public JobRoleDto() {
  }

  public JobRoleDto(String baseId, boolean isActive, String jobRoleCode, String jobRoleName) {
    super(baseId, isActive);
    this.jobRoleCode = jobRoleCode;
    this.jobRoleName = jobRoleName;
  }

  public String getJobRoleCode() {
    return jobRoleCode;
  }

  public void setJobRoleCode(String jobRoleCode) {
    this.jobRoleCode = jobRoleCode;
  }

  public String getJobRoleName() {
    return jobRoleName;
  }

  public void setJobRoleName(String jobRoleName) {
    this.jobRoleName = jobRoleName;
  }

  public static JobRoleDto toDto(JobRole jobRole) {
    return new JobRoleDto(jobRole.getBaseId(),
            jobRole.isActive(),
            jobRole.getJobRoleCode(),
            jobRole.getJobRoleName());
  }

  public static List<JobRoleDto> toDto(List<JobRole> jobRoles) {
    List<JobRoleDto> jobRoleDtos = new ArrayList<>();

    for (JobRole jobRole : jobRoles) {
      jobRoleDtos.add(JobRoleDto.toDto(jobRole));
    }
    return jobRoleDtos;
  }
}
