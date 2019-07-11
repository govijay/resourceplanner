package com.sap.ariba.cts.model.dto;

import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.TaskType;
import com.sap.ariba.cts.model.entity.UserProjectsSchedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserProjectsScheduleDto extends BaseDto {

  private UserDetailsDto userDetails;

  private ProjectStaffingDto projectStaffing;

  private int totalHrs;

  private int hrsPerDay;

  private Date startDate;

  private Date endDate;

  private String notes;

  private TaskType taskType;

  public UserProjectsScheduleDto() {
  }

  public UserProjectsScheduleDto(String baseId, boolean isActive, UserDetailsDto userDetails,
                                 ProjectStaffingDto projectStaffing, int totalHrs,
                                 int hrsPerDay, Date startDate, Date endDate, String notes,
                                 TaskType taskType) {
    super(baseId, isActive);
    this.userDetails = userDetails;
    this.projectStaffing = projectStaffing;
    this.totalHrs = totalHrs;
    this.hrsPerDay = hrsPerDay;
    this.startDate = startDate;
    this.endDate = endDate;
    this.notes = notes;
    this.taskType = taskType;
  }

  public UserDetailsDto getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(UserDetailsDto userDetails) {
    this.userDetails = userDetails;
  }

  public ProjectStaffingDto getProjectStaffing() {
    return projectStaffing;
  }

  public void setProjectStaffing(ProjectStaffingDto projectStaffing) {
    this.projectStaffing = projectStaffing;
  }

  public int getTotalHrs() {
    return totalHrs;
  }

  public void setTotalHrs(int totalHrs) {
    this.totalHrs = totalHrs;
  }

  public int getHrsPerDay() {
    return hrsPerDay;
  }

  public void setHrsPerDay(int hrsPerDay) {
    this.hrsPerDay = hrsPerDay;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public TaskType getTaskType() {
    return taskType;
  }

  public void setTaskType(TaskType taskType) {
    this.taskType = taskType;
  }

  public static UserProjectsScheduleDto toDto(UserProjectsSchedule userProjectsSchedule) {
    UserProjectsScheduleDto userProjectsScheduleDto = new UserProjectsScheduleDto(
            userProjectsSchedule.getBaseId(),
            userProjectsSchedule.isActive(),
            UserDetailsDto.toDto(userProjectsSchedule.getUserDetails()),
            ProjectStaffingDto.toDto(userProjectsSchedule.getProjectStaffing()),
            userProjectsSchedule.getTotalHrs(),
            userProjectsSchedule.getHrsPerDay(),
            userProjectsSchedule.getStartDate(),
            userProjectsSchedule.getEndDate(),
            userProjectsSchedule.getNotes(),
            userProjectsSchedule.getTaskType()
            );

    return userProjectsScheduleDto;
  }

  public static List<UserProjectsScheduleDto> toDto(List<UserProjectsSchedule> userProjectsScheduleList) {
    List<UserProjectsScheduleDto> userProjectsScheduleDtos = new ArrayList<>();

    for (UserProjectsSchedule userProjectsSchedule : userProjectsScheduleList) {
      userProjectsScheduleDtos.add(UserProjectsScheduleDto.toDto(userProjectsSchedule));
    }
    return userProjectsScheduleDtos;
  }
}
