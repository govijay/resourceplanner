package com.sap.ariba.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.ariba.cts.model.UserInfo;
import com.sap.ariba.cts.repo.UserInfoRepo;

/**
 * 
 * UserInfo service that deals with User CRUD or queries.
 */
@Service
public class UserInfoService {

	@Autowired
	UserInfoRepo userInfoRepo;
	
	/**
	 * creates the user with given u
	 * @param user
	 */
	public void create(UserInfo user) {
		userInfoRepo.save(user);
	}
}
