package com.sap.ariba.cts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.repository.UserInfoRepository;
import com.sap.ariba.cts.response.BaseResponse.Status;
import com.sap.ariba.cts.response.UserResponse;
import com.sap.ariba.cts.service.UserInfoService;

/**
 * 
 * UserInfo service that deals with User CRUD or queries.
 */
@Service(value="userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	/**
	 * creates the user with given u
	 * @param user
	 */
	public UserResponse addUser(UserInfo user) {
		UserResponse response = new UserResponse();
	    UserInfo u = userInfoRepository.save(user);
		
		if(u != null) {response.setStatus(Status.OK);}
		else {response.setStatus(Status.ERROR);}
		
		return response;
	}
}
