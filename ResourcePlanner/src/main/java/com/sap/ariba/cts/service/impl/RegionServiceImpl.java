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
import com.sap.ariba.cts.utils.GenericUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
  public Region createRegion(Region region) {
    regionRepo.save(region);
    return (Region) regionRepo.findById(region.getBaseId()).get();
  }

  @Override
  public Region updateRegion(Region region) {
    Region regionToUpdate = regionRepo.getRegionByBaseId(region.getBaseId());

    if (regionToUpdate != null) {
      GenericUtil.copyNonNullProperties(region, regionToUpdate);
      regionRepo.save(regionToUpdate);
    }
    return (Region) regionRepo.findById(regionToUpdate.getBaseId()).get();
  }

  @Override
  public Region deactivateRegion(String baseId) {
    Region regionToDeactivate = regionRepo.getRegionByBaseId(baseId);
    if (regionToDeactivate != null) {
      regionToDeactivate.setActive(false);
      regionRepo.save(regionToDeactivate);
    }
    return (Region) regionRepo.findById(regionToDeactivate.getBaseId()).get();
  }

  @Override
  public Region reactivateRegion(String baseId) {
    Region regionToReactivate = regionRepo.getRegionByBaseId(baseId);
    if (regionToReactivate != null) {
      regionToReactivate.setActive(true);
      regionRepo.save(regionToReactivate);
    }
    return (Region) regionRepo.findById(regionToReactivate.getBaseId()).get();
  }

  @Override
  public Boolean deleteRegion(String baseId) {
    Region regionToHardDelete = (Region) regionRepo.findById(baseId).get();
    regionRepo.delete(regionToHardDelete);
    if (regionToHardDelete != null) {
      Collection<Department> departmentSet = (Collection<Department>) regionToHardDelete.getDepartments();
      if (!departmentSet.isEmpty()) {
        for (Department dept : departmentSet) {
          dept.setRegion(null);
        }
      }
    }
    return true;
  }

  @Override
  public Region getRegionByBaseId(String baseId) {
    return (Region) regionRepo.findById(baseId).get();
  }



  @Override
  public List<Region> getRegions() {
    return regionRepo.getActiveEntity();
  }

  @Override
  public Boolean isRegionActive(String baseId) {
    Optional<Region> regionOpt =  regionRepo.findById(baseId);
    if (regionOpt.isPresent()){
      return regionOpt.get().isActive();
    }
    return false;
  }

  @Override
  public Boolean isRegionExists(String baseId) {
    Optional<Region> regionOpt =  regionRepo.findById(baseId);
    if (regionOpt.isPresent()) return true;
    return false;
  }
}
