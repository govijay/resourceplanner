package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.model.entity.SubTeam;
import com.sap.ariba.cts.model.entity.Team;
import com.sap.ariba.cts.model.entity.UserDetails;
import com.sap.ariba.cts.model.entity.UserInfo;

import java.io.Serializable;
import java.util.List;

/**
 * The interface Department repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@Repository
public interface UserDetailsRepository<T, String extends Serializable> extends BaseEntityRepository<UserDetails, String> {

  @Override
  @Query("select usrd from UserDetails usrd where usrd.active=true")
  List<UserDetails> getActiveEntity();

  UserDetails getUserDetailsByBaseId(String baseId);

  UserDetails getUserDetailsByUserInfo(UserInfo userInfo);

  List<UserDetails> getUserDetailsByManagerInfo(UserInfo managerInfo);

  List<UserDetails> getUserDetailsByRegion(Region region);

  List<UserDetails> getUserDetailsByDepartment(Department department);

  List<UserDetails> getUserDetailsByTeam(Team team);

  List<UserDetails> getUserDetailsBySubTeam(SubTeam subTeam);

}
