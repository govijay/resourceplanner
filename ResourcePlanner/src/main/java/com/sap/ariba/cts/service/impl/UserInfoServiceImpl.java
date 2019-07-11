package com.sap.ariba.cts.service.impl;

import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.repository.UserInfoRepository;
import com.sap.ariba.cts.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserInfo service that deals with User CRUD or queries.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepo;

    /**
     * creates the user with given u
     *
     * @param user
     */
    public UserInfo addUser(UserInfo user) {
        UserInfo userInfo = userInfoRepo.save(user);
        return userInfo;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        UserInfo userInfoToUpdate = userInfoRepo.getUserInfoByUserId(userInfo.getUserId());
        if (userInfoToUpdate != null) {
            userInfoRepo.save(userInfoToUpdate);
        }
        return userInfoToUpdate;
    }

    @Override
    public UserInfo deactivateUserInfo(String userId) {
        UserInfo userInfoToDeactivate = userInfoRepo.getUserInfoByUserId(userId);
        if (userInfoToDeactivate != null) {
            userInfoToDeactivate.setActive(false);
            userInfoRepo.save(userInfoToDeactivate);
        }
        return (UserInfo) userInfoRepo.findById(userInfoToDeactivate.getUserId()).get();
    }

    @Override
    public UserInfo reactivateUserInfo(String userId) {
        UserInfo userInfoToReactivate = userInfoRepo.getUserInfoByUserId(userId);
        if (userInfoToReactivate != null) {
            userInfoToReactivate.setActive(true);
            userInfoRepo.save(userInfoToReactivate);
        }
        return (UserInfo) userInfoRepo.findById(userInfoToReactivate.getUserId()).get();
    }

    @Override
    public Boolean deleteUserInfo(String userId) {
        UserInfo userInfo = userInfoRepo.getUserInfoByUserId(userId);

        if (userInfo != null) {
            userInfoRepo.delete(userInfo);
        }
        return true;
    }

    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        return userInfoRepo.getUserInfoByUserId(userId);
    }

    @Override
    public List<UserInfo> getUserInfos() {
        return userInfoRepo.getUserInfoByActive(true);
    }

    @Override
    public Boolean isUserInfoExists(String userId) {
        return userInfoRepo.existsById(userId);
    }
}
