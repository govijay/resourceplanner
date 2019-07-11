package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.ProjectStaffing;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.model.entity.UserInfo;

import java.util.List;

public interface ProjectStaffingService {


    Long countProjectStaffing();


    ProjectStaffing createProjectStaffing(ProjectStaffing projectStaffing);

    ProjectStaffing updateProjectStaffing(ProjectStaffing projectStaffing);


    ProjectStaffing deactivateProjectStaffing(String baseId);


    ProjectStaffing reactivateProjectStaffing(String baseId);

    Boolean deleteProjectStaffing(String baseId);


    ProjectStaffing getProjectStaffingByBaseId(String baseId);


    List<ProjectStaffing> getProjectStaffingByCustomerName(String customerName);

    List<ProjectStaffing> getProjectStaffingByRegion(Region region);

    List<ProjectStaffing> getProjectStaffingByStaffOwner(UserInfo staffOwner);

    List<ProjectStaffing> getProjectStaffing();

    Boolean isProjectStaffingExists(String baseId);

    Boolean isProjectStaffingExistsForCustomerName(String customerName);

    Boolean isProjectStaffingActive(String baseId);
}
