package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.entity.UserInfo;

/**
 * 
 * Repository for dealing with User_Info table
 */
@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}
