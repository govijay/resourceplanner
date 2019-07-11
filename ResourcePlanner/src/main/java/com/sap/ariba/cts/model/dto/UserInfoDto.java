package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDto extends BaseDto {

  String userId;

  String firstName;

  String lastName;

  String middleName;

  String email;

  Date createdOn;

  Date lastModified;

  public UserInfoDto() {
  }

  public UserInfoDto(boolean isActive, String userId, String firstName, String lastName, String middleName, String email) {
    super(isActive);
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.email = email;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public static UserInfoDto toDto(UserInfo userInfo) {
    UserInfoDto userInfoDto = new UserInfoDto(
            userInfo.isActive(),
            userInfo.getUserId(),
            userInfo.getFirstName(),
            userInfo.getLastName(),
            userInfo.getMiddleName(),
            userInfo.getEmail());

    return userInfoDto;
  }

  public static List<UserInfoDto> toDto(List<UserInfo> userInfos) {
    List<UserInfoDto> userInfoDtos = new ArrayList<>();

    for (UserInfo userInfo : userInfos) {
      userInfoDtos.add(UserInfoDto.toDto(userInfo));
    }
    return userInfoDtos;
  }
}
