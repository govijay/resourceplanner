package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.ProjectStaffing;
import com.sap.ariba.cts.model.entity.UserDetails;
import com.sap.ariba.cts.model.entity.UserProjectsSchedule;

import java.util.Date;
import java.util.List;

public interface UserProjectsScheduleService {


  Long countUserProjectsSchedules();


  UserProjectsSchedule createUserProjectsSchedule(UserProjectsSchedule userProjectsSchedule);

  UserProjectsSchedule updateUserProjectsSchedule(UserProjectsSchedule userProjectsSchedule);


  UserProjectsSchedule deactivateUserProjectsSchedule(String baseId);


  UserProjectsSchedule reactivateUserProjectsSchedule(String baseId);

  Boolean deleteUserProjectsSchedule(String baseId);


  UserProjectsSchedule getUserProjectsScheduleByBaseId(String baseId);


  List<UserProjectsSchedule> getUserProjectsSchedulesByUserDetails(UserDetails userDetails);

  List<UserProjectsSchedule> getUserProjectsSchedulesByProjectStaffing(ProjectStaffing projectStaffing);

  List<UserProjectsSchedule> getUserProjectsScheduleBetweenStartDateAndEndDate(Date startDate, Date endDate);

  List<UserProjectsSchedule> getUserProjectsSchedules();

  Boolean isUserProjectsScheduleExists(String baseId);

  Boolean isUserProjectsScheduleActive(String baseId);
}
