package com.sap.ariba.cts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.repository.DepartmentRepository;
import com.sap.ariba.cts.repository.RegionRepository;
import com.sap.ariba.cts.service.DepartmentService;
import com.sap.ariba.cts.utils.GenericUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Department service.
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

  private static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

  @Autowired
  private RegionRepository regionRepo;

  @Autowired
  private DepartmentRepository departmentRepo;

  @Override
  public Long countDepartments() {
    return departmentRepo.count();
  }

  @Override
  public Department createDepartment(Department dept) {

    String regionId = dept.getRegionBaseId();
    if (regionId != null) {
      logger.info(regionId);
      Region regionFromDB = regionRepo.getRegionByBaseId(regionId);
      if (regionFromDB != null) {
        dept.setRegion(regionFromDB);
      }
    }
    departmentRepo.save(dept);
    return (Department) departmentRepo.findById(dept.getBaseId()).get();
  }

  @Override
  public Department updateDepartment(Department dept) {

    Region regionFromDb = null;
    String regionId = dept.getRegionBaseId();
    if (regionId != null) {
      regionFromDb = regionRepo.getRegionByBaseId(regionId);
    }

    Department deptToUpdate = departmentRepo.getDepartmentByBaseId(dept.getBaseId());

    if (deptToUpdate != null) {
      GenericUtil.copyNonNullProperties(dept, deptToUpdate);
      if (regionFromDb != null) {
        deptToUpdate.setRegion(regionFromDb);
      }
    }
    departmentRepo.save(deptToUpdate);
    return deptToUpdate;
  }

  @Override
  public Department deactivateDepartment(String baseId) {
    Department deptToDeactivate = departmentRepo.getDepartmentByBaseId(baseId);

    if (deptToDeactivate != null) {
      deptToDeactivate.setActive(false);
      departmentRepo.save(deptToDeactivate);
    }
    return (Department) departmentRepo.findById(baseId).get();
  }

  @Override
  public Department reactivateDepartment(String baseId) {
   Department deptToReactivate = departmentRepo.getDepartmentByBaseId(baseId);
    if (deptToReactivate != null) {
      deptToReactivate.setActive(true);
      departmentRepo.save(deptToReactivate);
    }
    return (Department) departmentRepo.findById(baseId).get();
  }

  @Override
  public Boolean deleteDepartment(String baseId) {
    Department deptToHardDelete = departmentRepo.getDepartmentByBaseId(baseId);

    if (deptToHardDelete != null) {
      departmentRepo.delete(deptToHardDelete);
    }
    return true;
  }


  @Override
  public Department getDepartmentByBaseId(String baseId) {
    return departmentRepo.getDepartmentByBaseId(baseId);
  }


  @Override
  public List<Department> getDepartments() {
    return departmentRepo.getActiveEntity();
  }

  @Override
  public List<Department> getDepartmentsByRegionBaseId(String regionBaseId) {
    List<Department> departments = new ArrayList<>();

    Region region = regionRepo.getRegionByBaseId(regionBaseId);

    if (region != null) {
      departments = (List<Department>) departmentRepo.getDepartmentsByRegion(region);
    }
    return departments;
  }

  @Override
  public Region getRegionByDeptBaseId(String baseId) {

   Region region = null;
    Department department = departmentRepo.getDepartmentByBaseId(baseId);
    if (department != null) {
      region = department.getRegion();
    }
    return region;
  }

  @Override
  public Boolean isDepartmentActive(String baseId) {
    Department department = departmentRepo.getDepartmentByBaseId(baseId);
    if (department != null) return department.isActive();
    return false;
  }



  @Override
  public Boolean isDepartmentExists(String baseId) {
    Department department = departmentRepo.getDepartmentByBaseId(baseId);
    if (department != null) return true;
    return false;
  }
}
