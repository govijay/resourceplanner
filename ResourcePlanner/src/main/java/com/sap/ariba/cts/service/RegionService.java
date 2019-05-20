package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.Region;

import java.util.List;

/**
 * The interface Region service.
 *
 * @author Vijay.G
 * @version $Id : $
 */
public interface RegionService {

  /**
   * Count regions long.
   *
   * @return the long
   */
  Long countRegions();

  /**
   * Create region.
   *
   * @param region the region
   */
  void createRegion(Region region);

  /**
   * Update region.
   *
   * @param region the region
   */
  void updateRegion(Region region);

  /**
   * Deactivate region.
   *
   * @param regionCode the region code
   */
  void deactivateRegion(String regionCode);

  /**
   * Reactivate region.
   *
   * @param regionCode the region code
   */
  void reactivateRegion(String regionCode);

  /**
   * Delete region.
   *
   * @param regionCode the region code
   */
  void deleteRegion(String regionCode);

  /**
   * Is region active boolean.
   *
   * @param regionCode the region code
   * @return the boolean
   */
  boolean isRegionActive(String regionCode);

  /**
   * Is region exists boolean.
   *
   * @param regionCode the region code
   * @return the boolean
   */
  boolean isRegionExists(String regionCode);

  /**
   * Gets region by code.
   *
   * @param regionCode the region code
   * @return the region by code
   */
  Region getRegionByCode(String regionCode);

  /**
   * Gets region name by region code.
   *
   * @param regionCode the region code
   * @return the region name by region code
   */
  String getRegionNameByRegionCode(String regionCode);

  /**
   * Gets regions.
   *
   * @return the regions
   */
  List<Region> getRegions();

}
