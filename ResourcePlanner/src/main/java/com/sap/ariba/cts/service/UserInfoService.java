package com.sap.ariba.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.repository.UserInfoRepository;

/**
 * 
 * UserInfo service that deals with User CRUD or queries.
 */
@Service
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	/**
	 * creates the user with given u
	 * @param user
	 */
	public void create(UserInfo user) {
		userInfoRepository.save(user);
	}
}
