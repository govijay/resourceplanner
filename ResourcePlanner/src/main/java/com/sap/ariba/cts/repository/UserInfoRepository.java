package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.UserInfo;

import java.util.List;

/**
 * 
 * Repository for dealing with User_Info table
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

  List<UserInfo> getUserInfoByActive(boolean active);

  UserInfo getUserInfoByUserId(String userId);

  UserInfo getUserInfoByFirstName(String firstName);

  UserInfo getUserInfoByLastName(String lastName);

  UserInfo getUserInfoByFirstNameAndLastName(String firstName,String lastName);

}
