package com.sap.ariba.cts.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ariba.cts.model.UserInfo;
import com.sap.ariba.cts.service.UserInfoService;

@RestController(value="users")
public class UserInfoController {
	
	Logger logger = Logger.getLogger(UserInfoController.class.getSimpleName());
	
	@Autowired
	UserInfoService userInfoService;
	
	/**
	 * 
	 * @param user
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void createUser(@RequestBody UserInfo user) {
		logger.info("create method is called");
		userInfoService.create(user);
		ResponseEntity.ok();
	}
	

}
