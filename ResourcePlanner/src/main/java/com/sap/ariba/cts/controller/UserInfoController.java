package com.sap.ariba.cts.controller;

import com.sap.ariba.cts.model.dto.UserInfoDto;
import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.service.impl.UserInfoServiceImpl;
import com.sap.ariba.cts.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = Constants.USERINFO,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserInfoController {

    Logger logger = Logger.getLogger(UserInfoController.class.getSimpleName());

    @Autowired
    UserInfoServiceImpl userInfoService;

    @PostMapping
    public ResponseEntity<UserInfoDto> addUser(@RequestBody UserInfoDto userInfoDto) {
        logger.info("create method is called");
        UserInfo userInfo = userInfoService.addUser(UserInfo.toEntity(userInfoDto));
        return new ResponseEntity<>(UserInfoDto.toDto(userInfo), HttpStatus.OK);
    }


}
