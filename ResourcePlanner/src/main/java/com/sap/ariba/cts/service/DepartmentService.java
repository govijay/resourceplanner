package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Region;

import java.util.List;

/**
 * The interface Department service.
 *
 * @author Vijay.G
 * @version $Id : $
 */
public interface DepartmentService {

  /**
   * Count departments long.
   *
   * @return the long
   */
  Long countDepartments();

  /**
   * Create department.
   *
   * @param dept the dept
   */
  Department createDepartment(Department dept);

  /**
   * Update department.
   *
   * @param dept the dept
   */
  Department updateDepartment(Department dept);


  Department deactivateDepartment(String baseId);


  Department reactivateDepartment(String baseId);

  Boolean deleteDepartment(String baseId);


  Department getDepartmentByBaseId(String baseId);


  Region getRegionByDeptBaseId(String baseId);

  /**
   * Gets departments.
   *
   * @return the departments
   */
  List<Department> getDepartments();


  List<Department> getDepartmentsByRegionBaseId(String regionBaseId);


  Boolean isDepartmentActive(String baseId);

  Boolean isDepartmentExists(String baseId);

}
