package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.City;
import com.sap.ariba.cts.model.entity.Country;

import java.util.List;

public interface CityService {

  Long countCities();

  City createCity(City city);

  City updateCity(City city);

  City deactivateCity(String baseId);

  City reactivateCity(String baseId);

  Boolean deleteCity(String baseId);

  Boolean isCityActive(String baseId);

  Boolean isCityExists(String baseId);

  City getCityByBaseId(String baseId);

  Country getCountryByCityBaseId(String baseId);

  List<City> getCities();

  List<City> getCitiesByCountryBaseId(String ctryBaseId);
}
