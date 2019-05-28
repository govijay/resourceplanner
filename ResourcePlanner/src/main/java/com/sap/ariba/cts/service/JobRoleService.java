package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.JobRole;

import java.util.List;

/**
 * The interface Job Role service.
 *
 * @author Vijay.G
 * @version $Id : $
 */
public interface JobRoleService {

  Long countJobRoles();

  JobRole createJobRole(JobRole jobRole);

  JobRole updateJobRole(JobRole jobRole);

  JobRole deactivateJobRole(String baseId);

  JobRole reactivateJobRole(String baseId);

  Boolean deleteJobRole(String baseId);


  Boolean isJobRoleActive(String baseId);

  Boolean isJobRoleExists(String baseId);


  JobRole getJobRoleByBaseId(String baseId);

  List<JobRole> getJobRoles();

}
