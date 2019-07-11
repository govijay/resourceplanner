package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.UserDetails;

import java.util.List;

/**
 * The interface Department service.
 *
 * @author Vijay.G
 * @version $Id : $
 */
public interface UserDetailsService {

  Long countUserDetails();


  UserDetails createUserDetails(UserDetails userDetails);

  UserDetails updateUserDetails(UserDetails userDetails);


  UserDetails deactivateUserDetails(String baseId);


  UserDetails reactivateUserDetails(String baseId);

  Boolean deleteUserDetails(String baseId);


  UserDetails getUserDetailsByBaseId(String baseId);


  UserDetails getUserDetailsByUserId(String userId);

  List<UserDetails> getUserDetails();

  Boolean isUserDetailsExists(String baseId);

  Boolean isUserDetailsExistsForUser(String userId);

  Boolean isUserDetailsActive(String userId);

}
