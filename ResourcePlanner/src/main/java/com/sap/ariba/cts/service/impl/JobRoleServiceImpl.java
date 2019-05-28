package com.sap.ariba.cts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.entity.JobRole;
import com.sap.ariba.cts.repository.JobRoleRepository;
import com.sap.ariba.cts.service.JobRoleService;
import com.sap.ariba.cts.utils.GenericUtil;

import java.util.List;

@Service
@Transactional
public class JobRoleServiceImpl implements JobRoleService {

  private static Logger logger = LoggerFactory.getLogger(JobRoleServiceImpl.class);

  @Autowired
  private JobRoleRepository jobRoleRepo;

  @Override
  public Long countJobRoles() {
    return jobRoleRepo.count();
  }

  @Override
  public JobRole createJobRole(JobRole jobRole) {
    jobRoleRepo.save(jobRole);
    return (JobRole) jobRoleRepo.findById(jobRole.getBaseId()).get();
  }

  @Override
  public JobRole updateJobRole(JobRole jobRole) {
    JobRole jobRoleToUpdate = jobRoleRepo.getJobRoleByBaseId(jobRole.getBaseId());

    if (jobRoleToUpdate != null) {
      GenericUtil.copyNonNullProperties(jobRole, jobRoleToUpdate);
      jobRoleRepo.save(jobRoleToUpdate);
    }
    return (JobRole) jobRoleRepo.findById(jobRoleToUpdate.getBaseId()).get();
  }

  @Override
  public JobRole deactivateJobRole(String baseId) {
    JobRole jobRoleToDeactivate = jobRoleRepo.getJobRoleByBaseId(baseId);
    if (jobRoleToDeactivate != null) {
      jobRoleToDeactivate.setActive(false);
      jobRoleRepo.save(jobRoleToDeactivate);
    }
    return (JobRole) jobRoleRepo.findById(jobRoleToDeactivate.getBaseId()).get();
  }

  @Override
  public JobRole reactivateJobRole(String baseId) {
    JobRole jobRoleToDeactivate = jobRoleRepo.getJobRoleByBaseId(baseId);
    if (jobRoleToDeactivate != null) {
      jobRoleToDeactivate.setActive(true);
      jobRoleRepo.save(jobRoleToDeactivate);
    }
    return (JobRole) jobRoleRepo.findById(jobRoleToDeactivate.getBaseId()).get();

  }

  @Override
  public Boolean deleteJobRole(String baseId) {
    JobRole jobRoleToDelete = jobRoleRepo.getJobRoleByBaseId(baseId);

    if (jobRoleToDelete != null) {
      jobRoleRepo.delete(jobRoleToDelete);
    }
    return true;
  }

  @Override
  public Boolean isJobRoleActive(String baseId) {
    JobRole jobRole = jobRoleRepo.getJobRoleByBaseId(baseId);
    if (jobRole != null) return jobRole.isActive();
    return false;
  }

  @Override
  public Boolean isJobRoleExists(String baseId) {
    JobRole jobRole = jobRoleRepo.getJobRoleByBaseId(baseId);
    if (jobRole != null) return true;
    return false;
  }

  @Override
  public JobRole getJobRoleByBaseId(String baseId) {
    return jobRoleRepo.getJobRoleByBaseId(baseId);
  }

  @Override
  public List<JobRole> getJobRoles() {
    return jobRoleRepo.getActiveEntity();
  }
}
