package com.sap.ariba.cts.service.impl;

import com.sap.ariba.cts.model.entity.*;
import com.sap.ariba.cts.repository.*;
import com.sap.ariba.cts.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDetailsRepository usrDetailsRepo;

    @Autowired
    private UserInfoRepository usrInfoRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    @Autowired
    private RegionRepository regRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private SubTeamRepository subTeamRepo;

    @Override
    public Long countUserDetails() {
        return usrDetailsRepo.count();
    }

    @Override
    public UserDetails createUserDetails(UserDetails userDetails) {

        String userId = userDetails.getUserId();
        if (userId != null) {
            logger.info(userId);
            UserInfo userInfo = usrInfoRepo.findById(userId).get();
            logger.info("User Info" + userInfo);
            if (userInfo != null) userDetails.setUserInfo(userInfo);
        }

        Department departmentFromReq = userDetails.getDepartment();

        if (departmentFromReq != null) {
            logger.info("departmentFromReq " + departmentFromReq);
            Department deptFromDB = deptRepo.getDepartmentByBaseId(departmentFromReq.getBaseId());
            if (deptFromDB != null) userDetails.setDepartment(deptFromDB);
        }

        Region regionFromReq = userDetails.getRegion();

        if (regionFromReq != null) {
            logger.info("regionFromReq " + regionFromReq);
            Region regionFromDB = regRepo.getRegionByBaseId(regionFromReq.getBaseId());
            if (regionFromDB != null) userDetails.setRegion(regionFromDB);
        }

        Team teamFromReq = userDetails.getTeam();

        if (teamFromReq != null) {
            logger.info("teamFromReq " + teamFromReq);
            Team teamFromDB = teamRepo.getTeamByBaseId(teamFromReq.getBaseId());
            if (teamFromDB != null) userDetails.setTeam(teamFromDB);
        }

        SubTeam subTeamFromReq = userDetails.getSubTeam();

        if (subTeamFromReq != null) {
            logger.info("subTeamFromReq " + subTeamFromReq);
            SubTeam subTeamFromDB = subTeamRepo.getSubTeamByBaseId(subTeamFromReq.getBaseId());
            if (subTeamFromDB != null) userDetails.setSubTeam(subTeamFromDB);
        }

        usrDetailsRepo.save(userDetails);
        return (UserDetails) usrDetailsRepo.findById(userDetails.getBaseId()).get();
    }

    @Override
    public UserDetails updateUserDetails(UserDetails userDetails) {
        return null;
    }

    @Override
    public UserDetails deactivateUserDetails(String baseId) {
        return null;
    }

    @Override
    public UserDetails reactivateUserDetails(String baseId) {
        return null;
    }

    @Override
    public Boolean deleteUserDetails(String baseId) {
        return null;
    }

    @Override
    public UserDetails getUserDetailsByBaseId(String baseId) {
        return usrDetailsRepo.getUserDetailsByBaseId(baseId);
    }

    @Override
    public UserDetails getUserDetailsByUserId(String userId) {
        UserInfo userInfo = usrInfoRepo.findById(userId).get();
        return usrDetailsRepo.getUserDetailsByUserInfo(userInfo);
    }

    @Override
    public List<UserDetails> getUserDetails() {
        return usrDetailsRepo.getActiveEntity();
    }

    @Override
    public Boolean isUserDetailsExists(String baseId) {
        UserDetails userDetails = usrDetailsRepo.getUserDetailsByBaseId(baseId);
        if (userDetails != null) return true;
        return false;
    }

    @Override
    public Boolean isUserDetailsExistsForUser(String userId) {
        UserInfo userInfo = usrInfoRepo.findById(userId).get();
        UserDetails userDetails = usrDetailsRepo.getUserDetailsByUserInfo(userInfo);
        if (userDetails != null) return true;
        return false;
    }

    @Override
    public Boolean isUserDetailsActive(String userId) {
        return null;
    }
}
