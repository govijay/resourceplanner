package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.ProjectStaffing;
import com.sap.ariba.cts.model.entity.UserDetails;
import com.sap.ariba.cts.model.entity.UserProjectsSchedule;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository
public interface UserProjectsScheduleRepository<T, String extends Serializable> extends BaseEntityRepository<UserProjectsSchedule, String> {

  @Override
  @Query("select ups from UserProjectsSchedule ups where ups.active=true")
  List<UserProjectsSchedule> getActiveEntity();

  UserProjectsSchedule getUserProjectsScheduleByBaseId(String baseId);

  List<UserProjectsSchedule> getUserProjectsScheduleByUserDetails(UserDetails userDetails);

  List<UserProjectsSchedule> getUserProjectsScheduleByProjectStaffing(ProjectStaffing projectStaffing);

  List<UserProjectsSchedule> findByStartDateGreaterThanEqualAndEndDateLessThanEqual (Date startDate, Date endDate);

}
