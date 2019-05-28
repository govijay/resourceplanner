package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.UserDetails;
import com.sap.ariba.cts.model.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDto extends BaseDto {

  @JsonProperty("userid")
  String userId;

  @JsonProperty("userinfo")
  UserInfo userInfo;

  @JsonProperty("region")
  RegionDto region;

  @JsonProperty("department")
  DepartmentDto department;

  @JsonProperty("team")
  TeamDto team;

  @JsonProperty("subteam")
  SubTeamDto subTeam;

  public UserDetailsDto() {
  }

  public UserDetailsDto(String baseId, boolean isActive, String userId, UserInfo userInfo, RegionDto region, DepartmentDto department, TeamDto team, SubTeamDto subTeam) {
    super(baseId, isActive);
    this.userId = userId;
    this.userInfo = userInfo;
    this.region = region;
    this.department = department;
    this.team = team;
    this.subTeam = subTeam;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public RegionDto getRegion() {
    return region;
  }

  public void setRegion(RegionDto region) {
    this.region = region;
  }

  public DepartmentDto getDepartment() {
    return department;
  }

  public void setDepartment(DepartmentDto department) {
    this.department = department;
  }

  public TeamDto getTeam() {
    return team;
  }

  public void setTeam(TeamDto team) {
    this.team = team;
  }

  public SubTeamDto getSubTeam() {
    return subTeam;
  }

  public void setSubTeam(SubTeamDto subTeam) {
    this.subTeam = subTeam;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public static UserDetailsDto toDto(UserDetails userDetails) {

    UserDetailsDto userDetailsDto = new UserDetailsDto(userDetails.getBaseId(),
            userDetails.isActive(),
            userDetails.getUserId(),
            userDetails.getUserInfo(),
            RegionDto.toDto(userDetails.getRegion()),
            DepartmentDto.toDto(userDetails.getDepartment()),
            TeamDto.toDto(userDetails.getTeam()),
            SubTeamDto.toDto(userDetails.getSubTeam()));

    return userDetailsDto;
  }

  public static List<UserDetailsDto> toDto(List<UserDetails> userDetails) {
    List<UserDetailsDto> userDetailsDtos = new ArrayList<>();

    for (UserDetails userDetail : userDetails) {
      userDetailsDtos.add(UserDetailsDto.toDto(userDetail));
    }
    return userDetailsDtos;
  }
}
