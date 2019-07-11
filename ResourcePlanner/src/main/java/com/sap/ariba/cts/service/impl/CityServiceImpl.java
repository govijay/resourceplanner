package com.sap.ariba.cts.service.impl;

import com.sap.ariba.cts.model.entity.City;
import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.repository.CityRepository;
import com.sap.ariba.cts.repository.CountryRepository;
import com.sap.ariba.cts.service.CityService;
import com.sap.ariba.cts.utils.GenericUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private static Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    private CountryRepository countryRepo;

    @Autowired
    private CityRepository cityRepo;

    @Override
    public Long countCities() {
        return cityRepo.count();
    }

    @Override
    public City createCity(City city) {
        String ctryId = city.getCountryBaseId();
        if (ctryId != null) {
            logger.info(ctryId);
            Country ctryFromDB = countryRepo.getCountryByBaseId(ctryId);
            if (ctryFromDB != null) {
                city.setCountry(ctryFromDB);
            }
        }
        cityRepo.save(city);
        return (City) cityRepo.findById(city.getBaseId()).get();
    }

    @Override
    public City updateCity(City city) {
        Country ctryFromDB = null;
        String ctryId = city.getCountryBaseId();
        if (ctryId != null) {
            ctryFromDB = countryRepo.getCountryByBaseId(ctryId);
        }

        City cityToUpdate = cityRepo.getCityByBaseId(city.getBaseId());

        if (cityToUpdate != null) {
            GenericUtil.copyNonNullProperties(city, cityToUpdate);
            if (ctryFromDB != null) {
                cityToUpdate.setCountry(ctryFromDB);
            }
        }
        cityRepo.save(cityToUpdate);
        return cityToUpdate;
    }

    @Override
    public City deactivateCity(String baseId) {

        City cityToDeactivate = cityRepo.getCityByBaseId(baseId);

        if (cityToDeactivate != null) {
            cityToDeactivate.setActive(false);
            cityRepo.save(cityToDeactivate);
        }
        return (City) cityRepo.findById(baseId).get();
    }

    @Override
    public City reactivateCity(String baseId) {

        City cityToReactivate = cityRepo.getCityByBaseId(baseId);

        if (cityToReactivate != null) {
            cityToReactivate.setActive(true);
            cityRepo.save(cityToReactivate);
        }
        return (City) cityRepo.findById(baseId).get();
    }

    @Override
    public Boolean deleteCity(String baseId) {
        City cityToHardDelete = cityRepo.getCityByBaseId(baseId);

        if (cityToHardDelete != null) {
            cityRepo.delete(cityToHardDelete);
        }
        return true;
    }

    @Override
    public Boolean isCityActive(String baseId) {
        City city = cityRepo.getCityByBaseId(baseId);
        if (city != null) return city.isActive();
        return false;
    }

    @Override
    public Boolean isCityExists(String baseId) {
        City city = cityRepo.getCityByBaseId(baseId);
        if (city != null) return true;
        return false;
    }

    @Override
    public City getCityByBaseId(String baseId) {
        return cityRepo.getCityByBaseId(baseId);
    }

    @Override
    public Country getCountryByCityBaseId(String baseId) {
        City city = cityRepo.getCityByBaseId(baseId);
        return city.getCountry();
    }

    @Override
    public List<City> getCities() {
        return cityRepo.getActiveEntity();
    }

    @Override
    public List<City> getCitiesByCountryBaseId(String ctryBaseId) {
        List<City> cities = new ArrayList<>();

        Country country = countryRepo.getCountryByBaseId(ctryBaseId);

        if (country != null) {
            cities = (List<City>) cityRepo.getCitiesByCountry(country);
        }
        return cities;
    }
}
