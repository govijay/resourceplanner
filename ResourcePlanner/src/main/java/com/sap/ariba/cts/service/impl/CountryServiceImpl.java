package com.sap.ariba.cts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.repository.CountryRepository;
import com.sap.ariba.cts.repository.RegionRepository;
import com.sap.ariba.cts.service.CountryService;
import com.sap.ariba.cts.utils.GenericUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

  private static Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

  @Autowired
  private CountryRepository countryRepo;

  @Autowired
  private RegionRepository regionRepo;

  @Override
  public Long countCountries() {
    return countryRepo.count();
  }

  @Override
  public Country createCountry(Country country) {
    String regionId = country.getRegionBaseId();
    if (regionId != null) {
      logger.info(regionId);
      Region regionFromDB = regionRepo.getRegionByBaseId(regionId);
      if (regionFromDB != null) {
        country.setRegion(regionFromDB);
      }
    }
    countryRepo.save(country);
    return (Country) countryRepo.findById(country.getBaseId()).get();
  }

  @Override
  public Country updateCountry(Country country) {
    Region regionFromDb = null;
    String regionId = country.getRegionBaseId();
    if (regionId != null) {
      regionFromDb = regionRepo.getRegionByBaseId(regionId);
    }

    Country ctryToUpdate = countryRepo.getCountryByBaseId(country.getBaseId());

    if (ctryToUpdate != null) {
      GenericUtil.copyNonNullProperties(country, ctryToUpdate);
      ctryToUpdate.setActive(country.isActive());
      ctryToUpdate.setCountryCode(country.getCountryCode());
      ctryToUpdate.setCountryName(country.getCountryName());
      if (regionFromDb != null) {
        ctryToUpdate.setRegion(regionFromDb);
      }
    }
    countryRepo.save(ctryToUpdate);
    return ctryToUpdate;
  }

  @Override
  public Country deactivateCountry(String baseId) {

    Country ctryToDeactivate = countryRepo.getCountryByBaseId(baseId);

    if (ctryToDeactivate != null) {
      ctryToDeactivate.setActive(false);
      countryRepo.save(ctryToDeactivate);
    }
    return (Country) countryRepo.findById(baseId).get();
  }

  @Override
  public Country reactivateCountry(String baseId) {

    Country ctryToReactivate = countryRepo.getCountryByBaseId(baseId);
    if (ctryToReactivate != null) {
      ctryToReactivate.setActive(true);
      countryRepo.save(ctryToReactivate);
    }
    return (Country) countryRepo.findById(baseId).get();
  }

  @Override
  public Boolean deleteCountry(String baseId) {
    Country ctryToHardDelete = countryRepo.getCountryByBaseId(baseId);

    if (ctryToHardDelete != null) {
      countryRepo.delete(ctryToHardDelete);
    }
    return true;
  }

  @Override
  public Boolean isCountryActive(String baseId) {
    Country country = countryRepo.getCountryByBaseId(baseId);
    if (country != null) return country.isActive();
    return false;
  }

  @Override
  public Boolean isCountryExists(String baseId) {
    Country country = countryRepo.getCountryByBaseId(baseId);
    if (country != null) return true;
    return false;
  }

  @Override
  public Country getCountryByBaseId(String baseId) {
    return countryRepo.getCountryByBaseId(baseId);
  }

  @Override
  public Region getRegionByCountryBaseId(String baseId) {
    Country country = countryRepo.getCountryByBaseId(baseId);
    return country.getRegion();
  }

  @Override
  public List<Country> getCountries() {
    return countryRepo.getActiveEntity();
  }

  @Override
  public List<Country> getCountriesByRegionBaseId(String regionBaseId) {
    List<Country> countries = new ArrayList<>();

    Region region = regionRepo.getRegionByBaseId(regionBaseId);

    if (region != null) {
      countries = (List<Country>) countryRepo.getCountriesByRegion(region);
    }
    return countries;
  }
}
