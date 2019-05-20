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
  void createDepartment(Department dept);

  /**
   * Update department.
   *
   * @param dept the dept
   */
  void updateDepartment(Department dept);

  /**
   * Deactivate department.
   *
   * @param deptCode   the dept code
   * @param regionCode the region code
   */
  void deactivateDepartment(String deptCode, String regionCode);

  /**
   * Reactivate department.
   *
   * @param deptCode   the dept code
   * @param regionCode the region code
   */
  void reactivateDepartment(String deptCode, String regionCode);

  /**
   * Delete department.
   *
   * @param deptCode   the dept code
   * @param regionCode the region code
   */
  void deleteDepartment(String deptCode, String regionCode);

  /**
   * Gets department by code.
   *
   * @param deptCode   the dept code
   * @param regionCode the region code
   * @return the department by code
   */
  Department getDepartmentByCode(String deptCode, String regionCode);

  /**
   * Gets region by dept code.
   *
   * @param deptCode the dept code
   * @return the region by dept code
   */
  List<Region> getRegionByDeptCode(String deptCode);

  /**
   * Gets department name by code.
   *
   * @param deptCode   the dept code
   * @param regionCode the region code
   * @return the department name by code
   */
  String getDepartmentNameByCode(String deptCode, String regionCode);

  /**
   * Gets departments.
   *
   * @return the departments
   */
  List<Department> getDepartments();

  /**
   * Gets department by region code.
   *
   * @param regionCode the region code
   * @return the department by region code
   */
  List<Department> getDepartmentByRegionCode(String regionCode);

  /**
   * Is department active boolean.
   *
   * @param deptCode   the dept code
   * @param regionCode the region code
   * @return the boolean
   */
  boolean isDepartmentActive(String deptCode, String regionCode);

}
