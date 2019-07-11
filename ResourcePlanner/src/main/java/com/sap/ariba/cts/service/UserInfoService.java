package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo addUser(UserInfo userInfo);

    UserInfo updateUserInfo(UserInfo userInfo);


    UserInfo deactivateUserInfo(String userId);


    UserInfo reactivateUserInfo(String userId);

    Boolean deleteUserInfo(String userId);


    UserInfo getUserInfoByUserId(String userId);

    List<UserInfo> getUserInfos();

    Boolean isUserInfoExists(String userId);

}
