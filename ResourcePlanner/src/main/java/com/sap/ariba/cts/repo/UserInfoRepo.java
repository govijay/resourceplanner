package com.sap.ariba.cts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.UserInfo;

/**
 * 
 * Repository for dealing with User_Info table
 */
@Repository
@Transactional
public interface UserInfoRepo extends JpaRepository<UserInfo, String> {

}
