package com.sap.ariba.cts.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sap.ariba.cts.model.entity.City;
import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.model.entity.UserInfo;
import com.sap.ariba.cts.repository.CityRepository;
import com.sap.ariba.cts.repository.CountryRepository;
import com.sap.ariba.cts.repository.RegionRepository;
import com.sap.ariba.cts.repository.UserInfoRepository;
import com.sap.ariba.cts.service.SetUpService;

import java.io.File;
import java.util.Collections;
import java.util.List;


@Component
@ConditionalOnProperty(name = "ctsresourceplanner.load.initialmasterdata", havingValue = "true")
public class SetUpServiceImpl implements SetUpService {

  private static Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private RegionRepository regionRepo;

  @Autowired
  private CountryRepository countryRepo;

  @Autowired
  private CityRepository cityRepo;

  @Autowired
  private UserInfoRepository userInfoRepo;

  @PostConstruct
  private void setUpData() {
    setUpRegion();
    setUpCountries();
    setUpCities();
    setUpAdminUser();
  }


  @Override
  public void setUpRegion() {
    logger.info("Inside init region method");
    Resource resource = resourceLoader.getResource("classpath:" + "data/csv/regions.csv");
    logger.info("loading regions csv file");
    if (regionRepo.count() == 0) {
      List<Region> regions = loadObjectList(Region.class, resource);
      regionRepo.saveAll(regions);
      logger.info("regions saved");
    }

  }

  @Override
  public void setUpCountries() {
    logger.info("Inside init countries method");
    Resource resource = resourceLoader.getResource("classpath:" + "data/csv/countries.csv");
    logger.info("loading countries csv file");
    if (countryRepo.count() == 0) {
      List<Country> countries = loadObjectList(Country.class, resource);
      if (!countries.isEmpty()) {
        countries.forEach(country -> {
          String regionId = country.getRegionBaseId();
          if (regionId != null) {
            Region regionFromDB = regionRepo.getRegionByRegionCode(regionId);
            if (regionFromDB != null) {
              country.setRegion(regionFromDB);
            }
          }
        });
      }

      countryRepo.saveAll(countries);
      logger.info("countries saved");
    }

  }

  @Override
  public void setUpCities() {
    logger.info("Inside init cities method");
    Resource resource = resourceLoader.getResource("classpath:" + "data/csv/cities.csv");
    logger.info("loading cities csv file");
    if (cityRepo.count() == 0) {
      List<City> cities = loadObjectList(City.class, resource);
      if (!cities.isEmpty()) {
        cities.forEach(city -> {
          String countryCode = city.getCountryBaseId();
          if (countryCode != null) {
            Country ctryFromDB = countryRepo.getCountryByCountryCode(countryCode);
            if (ctryFromDB != null) {
              city.setCountry(ctryFromDB);
            }
          }
        });
      }

      countryRepo.saveAll(cities);
      logger.info("cities saved");
    }
  }

  @Override
  public void setUpAdminUser() {
    logger.info("Inside init admin user method");
    if (userInfoRepo.getUserInfoByUserId("admin") == null) {
      logger.info("admin user not exist");
      UserInfo userInfo = new UserInfo("admin",
              "Administrator",
              "CTS",
              "",
              "admin@admin.com",
              true);
      userInfoRepo.save(userInfo);
      logger.info("admin user saved");
    }
  }

  public static <T> List<T> loadObjectList(Class<T> type, Resource resource) {
    try {
      CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
      CsvMapper mapper = new CsvMapper();
      File file = resource.getFile();
      MappingIterator<T> readValues =
              mapper.readerFor(type).with(bootstrapSchema).readValues(file);
      return readValues.readAll();
    } catch (Exception e) {
      logger.error("Error occurred while loading object list from file " + resource, e);
      return Collections.emptyList();
    }
  }
}
