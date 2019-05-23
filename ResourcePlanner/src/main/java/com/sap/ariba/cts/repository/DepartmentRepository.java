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

  Department getDepartmentByBaseId(String baseId);

  Collection<Department> getDepartmentsByRegion(Region region);


}
