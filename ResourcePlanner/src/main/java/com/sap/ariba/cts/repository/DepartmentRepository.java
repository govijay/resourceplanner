package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Region;

import java.io.Serializable;
import java.util.Collection;
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
public interface DepartmentRepository<T, String extends Serializable> extends BaseEntityRepository<Department, String> {

  @Override
  @Query("select dept from Department dept where dept.active=true")
  List<Department> getActiveEntity();

  /**
   * Gets department by depart code.
   *
   * @param departCode the depart code
   * @return the department by depart code
   */
  Collection<Department> getDepartmentByDepartCode(String departCode);

  /**
   * Gets department by depart name.
   *
   * @param departName the depart name
   * @return the department by depart name
   */
  Department getDepartmentByDepartName(String departName);

  /**
   * Gets department by base id.
   *
   * @param baseId the base id
   * @return the department by base id
   */
  Department getDepartmentByBaseId(String baseId);

  /**
   * Gets department by depart code and region base id.
   *
   * @param departCode   the depart code
   * @param regionBaseId the region base id
   * @return the department by depart code and region base id
   */
  Department getDepartmentByDepartCodeAndRegionBaseId(String departCode, Region regionBaseId);

  /**
   * Gets departments by region base id.
   *
   * @param regionBaseId the region base id
   * @return the departments by region base id
   */
  Collection<Department> getDepartmentsByRegionBaseId(Region regionBaseId);


}
