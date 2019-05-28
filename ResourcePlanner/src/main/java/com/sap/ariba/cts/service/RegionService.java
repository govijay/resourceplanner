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
   * @return
   */
  Region createRegion(Region region);

  /**
   * Update region.
   *
   * @param region the region
   */
  Region updateRegion(Region region);

  /**
   * Deactivate region.
   *
   * @param regionCode the region code
   */
  Region deactivateRegion(String regionCode);

  /**
   * Reactivate region.
   *
   * @param regionCode the region code
   */
  Region reactivateRegion(String regionCode);

  /**
   * Delete region.
   *
   * @param regionCode the region code
   */
  Boolean deleteRegion(String regionCode);


  Boolean isRegionActive(String baseId);

  /**
   * Is region exists boolean.
   *
   * @param baseId the region code
   * @return the boolean
   */
  Boolean isRegionExists(String baseId);


  Region getRegionByBaseId(String baseId);



  /**
   * Gets regions.
   *
   * @return the regions
   */
  List<Region> getRegions();

}
