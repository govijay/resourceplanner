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

  private static Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

  @Autowired
  private RegionRepository regionRepo;

  @Autowired
  private DepartmentRepository departmentRepo;

  @Override
  public Long countDepartments() {
    return departmentRepo.count();
  }

  @Override
  public void createDepartment(Department dept) {
    Region regionFromReq = dept.getRegionBaseId();
    if (regionFromReq != null) {
      logger.info(regionFromReq.getRegionCode());
      Region regionFromDB = regionRepo.getRegionByRegionCode(regionFromReq.getRegionCode());
      if (regionFromDB != null) {
        logger.info(regionFromReq.getRegionCode());
        dept.setRegionBaseId(regionFromDB);
      }
    }
    departmentRepo.save(dept);
  }

  @Override
  public void updateDepartment(Department dept) {

    // dept name change
    // dept code change
    // dept's region change
    Region regionFromReq = dept.getRegionBaseId();
    Region regionFromDb = null;
    if (regionFromReq != null) {
      regionFromDb = regionRepo.getRegionByRegionCode(regionFromReq.getRegionCode());
    }
    Department deptToUpdate = departmentRepo.getDepartmentByDepartCodeAndRegionBaseId(dept.getDepartCode(), regionFromDb);

    if (deptToUpdate != null) {
      deptToUpdate.setDepartCode(dept.getDepartCode());
      deptToUpdate.setDepartName(dept.getDepartName());
      if (regionFromDb != null) {
        deptToUpdate.setRegionBaseId(regionFromDb);
      }
    }
    departmentRepo.save(deptToUpdate);
  }

  @Override
  public void deactivateDepartment(String deptCode, String regionCode) {
    Region regionFromDb = null;
    if (regionCode != null) {
      regionFromDb = regionRepo.getRegionByRegionCode(regionCode);
    }
    Department deptToDeactivate = departmentRepo.getDepartmentByDepartCodeAndRegionBaseId(deptCode, regionFromDb);

    if (deptToDeactivate != null) {
      deptToDeactivate.setActive(false);
      departmentRepo.save(deptToDeactivate);
    }

  }

  @Override
  public void reactivateDepartment(String deptCode, String regionCode) {
    Region regionFromDb = null;
    if (regionCode != null) {
      regionFromDb = regionRepo.getRegionByRegionCode(regionCode);
    }
    Department deptToReactivate = departmentRepo.getDepartmentByDepartCodeAndRegionBaseId(deptCode, regionFromDb);


    if (deptToReactivate != null) {
      deptToReactivate.setActive(true);
      departmentRepo.save(deptToReactivate);
    }

  }

  @Override
  public void deleteDepartment(String deptCode, String regionCode) {
    Region regionFromDb = null;
    if (regionCode != null) {
      regionFromDb = regionRepo.getRegionByRegionCode(regionCode);
    }
    Department deptToHardDelete = departmentRepo.getDepartmentByDepartCodeAndRegionBaseId(deptCode, regionFromDb);

    if (deptToHardDelete != null) {
      departmentRepo.delete(deptToHardDelete);
    }
  }


  @Override
  public Department getDepartmentByCode(String deptCode, String regionCode) {
    Region regionFromDb = null;
    if (regionCode != null) {
      regionFromDb = regionRepo.getRegionByRegionCode(regionCode);
    }
    return departmentRepo.getDepartmentByDepartCodeAndRegionBaseId(deptCode, regionFromDb);
  }


  @Override
  public List<Department> getDepartments() {
    return departmentRepo.getActiveEntity();
  }

  @Override
  public List<Department> getDepartmentByRegionCode(String regionCode) {
    List<Department> departments = new ArrayList<>();

    Region region = regionRepo.getRegionByRegionCode(regionCode);

    if (region != null) {
      departments = (List<Department>) departmentRepo.getDepartmentsByRegionBaseId(region);
    }
    return departments;
  }

  @Override
  public List<Region> getRegionByDeptCode(String deptCode) {
    List<Department> departments;
    List<Region> regions = new ArrayList<>();
    departments = (List<Department>) departmentRepo.getDepartmentByDepartCode(deptCode);
    if (!departments.isEmpty()) {
      for (Department department : departments) {
        regions.add(department.getRegionBaseId());
      }
    }
    return regions;
  }

  @Override
  public boolean isDepartmentActive(String deptCode, String regionCode) {
    Region regionFromDB = regionRepo.getRegionByRegionCode(regionCode);
    Department department = null;
    if (regionFromDB != null) {
      department = departmentRepo.getDepartmentByDepartCodeAndRegionBaseId(deptCode, regionFromDB);
    }
    if (department != null) {
      return department.isActive();
    }
    return false;
  }

  @Override
  public String getDepartmentNameByCode(String deptCode, String regionCode) {
    Region regionFromDB = regionRepo.getRegionByRegionCode(regionCode);
    Department department = null;
    if (regionFromDB != null) {
      department = departmentRepo.getDepartmentByDepartCodeAndRegionBaseId(deptCode, regionFromDB);
    }
    if (department != null) {
      return department.getDepartName();
    }
    return null;
  }
}
