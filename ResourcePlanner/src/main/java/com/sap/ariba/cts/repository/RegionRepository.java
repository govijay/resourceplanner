package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.Region;

import java.io.Serializable;
import java.util.List;

/**
 * The interface Region repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@Repository
public interface RegionRepository<T, String extends Serializable> extends BaseEntityRepository<Region, String> {

  @Override
  @Query("select r from Region r where r.active=true")
  List<Region> getActiveEntity();

  /**
   * Gets region by region code.
   *
   * @param regionCode the region code
   * @return the region by region code
   */
  Region getRegionByRegionCode(String regionCode);

  /**
   * Gets region by base id.
   *
   * @param baseId the base id
   * @return the region by base id
   */
  Region getRegionByBaseId(String baseId);

  /**
   * Gets region by region name.
   *
   * @param regionName the region name
   * @return the region by region name
   */
  Region getRegionByRegionName(String regionName);


/*
  java.lang.String departmentsByRegionCode = "select d from Department d , Region r "
          + "where d.regionBaseId = r.baseId "
          + "and r.regionCode = :regionCode "
          + "and d.active = true";
  @Query(departmentsByRegionCode)
  List<Department> getDepartments(@Param("regionCode") String regionCode);*/

}
