package com.sap.ariba.cts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.repository.RegionRepository;
import com.sap.ariba.cts.service.RegionService;

import java.util.Collection;
import java.util.List;

/**
 * The type Region service.
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService {

  private static Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

  @Autowired
  private RegionRepository regionRepo;

  @Override
  public Long countRegions() {
    return regionRepo.count();
  }

  @Override
  public void createRegion(Region region) {
    Region regionPersisted = regionRepo.getRegionByRegionCode(region.getRegionCode());

    if (regionPersisted == null) {
      regionRepo.save(region);
    }
  }

  @Override
  public void updateRegion(Region region) {
    Region regionToUpdate = regionRepo.getRegionByRegionCode(region.getRegionCode());

    if (regionToUpdate != null) {
      regionToUpdate.setRegionName(region.getRegionName());
      regionRepo.save(regionToUpdate);
    }
  }

  @Override
  public void deactivateRegion(String regionCode) {
    Region regionToDeactivate = regionRepo.getRegionByRegionCode(regionCode);
    if (regionToDeactivate != null) {
      regionToDeactivate.setActive(false);
      regionRepo.save(regionToDeactivate);
    }
  }

  @Override
  public void reactivateRegion(String regionCode) {
    Region regionToReactivate = regionRepo.getRegionByRegionCode(regionCode);
    if (regionToReactivate != null) {
      regionToReactivate.setActive(true);
      regionRepo.save(regionToReactivate);
    }
  }

  @Override
  public void deleteRegion(String regionCode) {
    Region regionToHardDelete = regionRepo.getRegionByRegionCode(regionCode);
    regionRepo.delete(regionToHardDelete);
    if (regionToHardDelete != null) {
      Collection<Department> departmentSet = (Collection<Department>) regionToHardDelete.getDepartments();
      if (!departmentSet.isEmpty()) {
        for (Department dept : departmentSet) {
          dept.setRegionBaseId(null);
        }
      }
    }
  }


  @Override
  public Region getRegionByCode(String regionCode) {
    return regionRepo.getRegionByRegionCode(regionCode);
  }

  @Override
  public String getRegionNameByRegionCode(String regionCode) {

    Region regionForName = regionRepo.getRegionByRegionCode(regionCode);
    if (regionForName != null) {
      return regionForName.getRegionName();
    }
    return null;
  }

  @Override
  public List<Region> getRegions() {
    return regionRepo.getActiveEntity();
  }

  @Override
  public boolean isRegionActive(String regionCode) {
    Region region = regionRepo.getRegionByRegionCode(regionCode);
    return region != null && region.isActive();
  }

  @Override
  public boolean isRegionExists(String regionCode) {
    Region region = regionRepo.getRegionByRegionCode(regionCode);
    if (region != null) return true;
    return false;
  }
}
