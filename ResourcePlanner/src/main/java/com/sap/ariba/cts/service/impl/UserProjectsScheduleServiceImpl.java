package com.sap.ariba.cts.service.impl;

import com.sap.ariba.cts.model.entity.ProjectStaffing;
import com.sap.ariba.cts.model.entity.UserDetails;
import com.sap.ariba.cts.model.entity.UserProjectsSchedule;
import com.sap.ariba.cts.repository.ProjectStaffingRepository;
import com.sap.ariba.cts.repository.UserDetailsRepository;
import com.sap.ariba.cts.repository.UserProjectsScheduleRepository;
import com.sap.ariba.cts.service.UserProjectsScheduleService;
import com.sap.ariba.cts.utils.GenericUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserProjectsScheduleServiceImpl implements UserProjectsScheduleService {

    private static Logger logger = LoggerFactory.getLogger(UserProjectsScheduleServiceImpl.class);

    @Autowired
    private UserProjectsScheduleRepository userProjectsScheduleRepo;

    @Autowired
    private ProjectStaffingRepository projectStaffingRepo;

    @Autowired
    private UserDetailsRepository userDetailsRepo;

    @Override
    public Long countUserProjectsSchedules() {
        return userProjectsScheduleRepo.count();
    }

    @Override
    public UserProjectsSchedule createUserProjectsSchedule(UserProjectsSchedule userProjectsSchedule) {
        userProjectsScheduleRepo.save(userProjectsSchedule);
        return (UserProjectsSchedule) userProjectsScheduleRepo.findById(userProjectsSchedule.getBaseId()).get();
    }

    @Override
    public UserProjectsSchedule updateUserProjectsSchedule(UserProjectsSchedule userProjectsSchedule) {
        UserProjectsSchedule upsToUpdate = userProjectsScheduleRepo.getUserProjectsScheduleByBaseId(userProjectsSchedule.getBaseId());

        if (upsToUpdate != null) {
            GenericUtil.copyNonNullProperties(userProjectsSchedule, upsToUpdate);
            userProjectsScheduleRepo.save(upsToUpdate);
        }
        return (UserProjectsSchedule) userProjectsScheduleRepo.findById(upsToUpdate.getBaseId()).get();
    }

    @Override
    public UserProjectsSchedule deactivateUserProjectsSchedule(String baseId) {
        UserProjectsSchedule upsToDeactivate = userProjectsScheduleRepo.getUserProjectsScheduleByBaseId(baseId);
        if (upsToDeactivate != null) {
            upsToDeactivate.setActive(false);
            userProjectsScheduleRepo.save(upsToDeactivate);
        }
        return (UserProjectsSchedule) userProjectsScheduleRepo.findById(upsToDeactivate.getBaseId()).get();
    }

    @Override
    public UserProjectsSchedule reactivateUserProjectsSchedule(String baseId) {
        UserProjectsSchedule upsToReactivate = userProjectsScheduleRepo.getUserProjectsScheduleByBaseId(baseId);
        if (upsToReactivate != null) {
            upsToReactivate.setActive(true);
            userProjectsScheduleRepo.save(upsToReactivate);
        }
        return (UserProjectsSchedule) userProjectsScheduleRepo.findById(upsToReactivate.getBaseId()).get();
    }

    @Override
    public Boolean deleteUserProjectsSchedule(String baseId) {
        UserProjectsSchedule upsToHardDelete = userProjectsScheduleRepo.getUserProjectsScheduleByBaseId(baseId);
        userProjectsScheduleRepo.delete(upsToHardDelete);
        return true;
    }

    @Override
    public UserProjectsSchedule getUserProjectsScheduleByBaseId(String baseId) {
        return userProjectsScheduleRepo.getUserProjectsScheduleByBaseId(baseId);
    }

    @Override
    public List<UserProjectsSchedule> getUserProjectsSchedulesByUserDetails(UserDetails userDetails) {
        return userProjectsScheduleRepo.getUserProjectsScheduleByUserDetails(userDetails);
    }

    @Override
    public List<UserProjectsSchedule> getUserProjectsSchedulesByProjectStaffing(ProjectStaffing projectStaffing) {
        return userProjectsScheduleRepo.getUserProjectsScheduleByProjectStaffing(projectStaffing);
    }

    @Override
    public List<UserProjectsSchedule> getUserProjectsScheduleBetweenStartDateAndEndDate(Date startDate, Date endDate) {
        return userProjectsScheduleRepo.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
    }

    @Override
    public List<UserProjectsSchedule> getUserProjectsSchedules() {
        return userProjectsScheduleRepo.getActiveEntity();
    }

    @Override
    public Boolean isUserProjectsScheduleExists(String baseId) {
        UserProjectsSchedule userProjectsSchedule = userProjectsScheduleRepo.getUserProjectsScheduleByBaseId(baseId);
        if (userProjectsSchedule != null) return true;
        return false;
    }

    @Override
    public Boolean isUserProjectsScheduleActive(String baseId) {
        UserProjectsSchedule userProjectsSchedule = userProjectsScheduleRepo.getUserProjectsScheduleByBaseId(baseId);
        if (userProjectsSchedule != null) userProjectsSchedule.isActive();
        return false;
    }
}
