package com.sap.ariba.cts.service.impl;

import com.sap.ariba.cts.model.entity.ProjectStaffing;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.repository.ProjectStaffingRepository;
import com.sap.ariba.cts.service.ProjectStaffingService;
import com.sap.ariba.cts.utils.GenericUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectStaffingServiceImpl implements ProjectStaffingService {

    private static Logger logger = LoggerFactory.getLogger(ProjectStaffingServiceImpl.class);

    @Autowired
    private ProjectStaffingRepository projectStaffingRepo;

    @Override
    public Long countProjectStaffing() {
        return projectStaffingRepo.count();
    }

    @Override
    public ProjectStaffing createProjectStaffing(ProjectStaffing projectStaffing) {
        projectStaffingRepo.save(projectStaffing);
        return (ProjectStaffing) projectStaffingRepo.findById(projectStaffing.getBaseId()).get();
    }

    @Override
    public ProjectStaffing updateProjectStaffing(ProjectStaffing projectStaffing) {
        ProjectStaffing staffingToUpdate = projectStaffingRepo.getProjectStaffingByBaseId(projectStaffing.getBaseId());

        if (projectStaffing != null) {
            GenericUtil.copyNonNullProperties(projectStaffing, staffingToUpdate);
            projectStaffingRepo.save(staffingToUpdate);
        }
        return (ProjectStaffing) projectStaffingRepo.findById(staffingToUpdate.getBaseId()).get();
    }

    @Override
    public ProjectStaffing deactivateProjectStaffing(String baseId) {
        ProjectStaffing staffingToDeactivate = projectStaffingRepo.getProjectStaffingByBaseId(baseId);
        if (staffingToDeactivate != null) {
            staffingToDeactivate.setActive(false);
            projectStaffingRepo.save(staffingToDeactivate);
        }
        return (ProjectStaffing) projectStaffingRepo.findById(staffingToDeactivate.getBaseId()).get();
    }

    @Override
    public ProjectStaffing reactivateProjectStaffing(String baseId) {
        ProjectStaffing staffingToReactivate = projectStaffingRepo.getProjectStaffingByBaseId(baseId);
        if (staffingToReactivate != null) {
            staffingToReactivate.setActive(true);
            projectStaffingRepo.save(staffingToReactivate);
        }
        return (ProjectStaffing) projectStaffingRepo.findById(staffingToReactivate.getBaseId()).get();
    }

    @Override
    public Boolean deleteProjectStaffing(String baseId) {
        ProjectStaffing staffingToHardDelete = projectStaffingRepo.getProjectStaffingByBaseId(baseId);
        projectStaffingRepo.delete(staffingToHardDelete);
        return true;
    }

    @Override
    public ProjectStaffing getProjectStaffingByBaseId(String baseId) {
        return projectStaffingRepo.getProjectStaffingByBaseId(baseId);
    }

    @Override
    public List<ProjectStaffing> getProjectStaffingByCustomerName(String customerName) {
        return projectStaffingRepo.getProjectStaffingByCustomerName(customerName);
    }

    @Override
    public List<ProjectStaffing> getProjectStaffingByRegion(Region region) {
        return projectStaffingRepo.getProjectStaffingByCustomerName(region);
    }

    @Override
    public List<ProjectStaffing> getProjectStaffingByStaffOwner(UserInfo staffOwner) {
        return projectStaffingRepo.getProjectStaffingByStaffOwner(staffOwner);
    }

    @Override
    public List<ProjectStaffing> getProjectStaffing() {
        return projectStaffingRepo.getActiveEntity();
    }

    @Override
    public Boolean isProjectStaffingExists(String baseId) {
        ProjectStaffing staffing = projectStaffingRepo.getProjectStaffingByBaseId(baseId);
        if (staffing != null) return true;
        return false;
    }

    @Override
    public Boolean isProjectStaffingExistsForCustomerName(String customerName) {
        List<ProjectStaffing> staffing = projectStaffingRepo.getProjectStaffingByCustomerName(customerName);
        if (staffing != null && !staffing.isEmpty()) return true;
        return false;
    }

    @Override
    public Boolean isProjectStaffingActive(String baseId) {
        ProjectStaffing staffing = projectStaffingRepo.getProjectStaffingByBaseId(baseId);
        if (staffing != null) return staffing.isActive();
        return false;
    }
}
