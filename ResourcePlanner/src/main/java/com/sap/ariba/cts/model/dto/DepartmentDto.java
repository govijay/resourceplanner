package com.sap.ariba.cts.model.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.Department;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto extends BaseDto {

  @NotEmpty
  @JsonProperty("code")
  String deptCode;

  @JsonProperty("name")
  String deptName;

  @JsonProperty("region")
  RegionDto regionDto;

  @JsonProperty("regionid")
  String regionId;

  @JsonProperty("teams")
  List<TeamDto> teamDtoList;

  public DepartmentDto() {
  }

  public DepartmentDto(String baseId, boolean isActive, String deptCode, String deptName) {
    super(baseId, isActive);
    this.deptCode = deptCode;
    this.deptName = deptName;
  }

  public String getDeptCode() {
    return deptCode;
  }

  public void setDeptCode(String deptCode) {
    this.deptCode = deptCode;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public RegionDto getRegionDto() {
    return regionDto;
  }

  public void setRegionDto(RegionDto regionDto) {
    this.regionDto = regionDto;
  }

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public List<TeamDto> getTeamDtoList() {
    return teamDtoList;
  }

  public void setTeamDtoList(List<TeamDto> teamDtoList) {
    this.teamDtoList = teamDtoList;
  }

  public static DepartmentDto toDto(Department department) {

    DepartmentDto departmentDto = new DepartmentDto(department.getBaseId(),
              department.isActive(),
              department.getDepartCode(),
              department.getDepartName());
    if(department.getRegion() != null){
      departmentDto.setRegionDto(RegionDto.toDto(department.getRegion()));
    }
    return departmentDto;
  }

  public static List<DepartmentDto> toDto(List<Department> departments) {
    List<DepartmentDto> departmentDtos = new ArrayList<>();

    for (Department department : departments) {
      departmentDtos.add(DepartmentDto.toDto(department));
    }

    return departmentDtos;
  }

}
