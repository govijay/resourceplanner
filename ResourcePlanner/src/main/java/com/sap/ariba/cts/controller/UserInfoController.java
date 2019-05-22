package com.sap.ariba.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.response.BaseResponse.Status;
import com.sap.ariba.cts.response.UserResponse;
import com.sap.ariba.cts.service.UserInfoService;
import com.sap.ariba.cts.utils.Constants;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = Constants.USERINFO, 
                consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces=MediaType.APPLICATION_JSON_VALUE)
public class UserInfoController {
	
	Logger logger = Logger.getLogger(UserInfoController.class.getSimpleName());
	
	@Autowired
	UserInfoService userInfoServiceImpl;
	
	@PostMapping
	public ResponseEntity<UserResponse> addUser(@RequestBody UserInfo user) {
		logger.info("create method is called");
		UserResponse response = userInfoServiceImpl.addUser(user);
		if(response.getStatus().equals(Status.OK)) {
		    return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
		} else {
		    return new ResponseEntity<UserResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
