package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.response.UserResponse;

public interface UserInfoService {
    UserResponse addUser(UserInfo userInfo);
}
