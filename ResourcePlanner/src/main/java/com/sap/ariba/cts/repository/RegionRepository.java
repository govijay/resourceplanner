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

  Region getRegionByBaseId(String baseId);

  Region getRegionByRegionCode(String regionCode);

}
