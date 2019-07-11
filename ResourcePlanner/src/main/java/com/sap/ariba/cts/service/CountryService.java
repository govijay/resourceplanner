package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.model.entity.Region;

import java.util.List;

public interface CountryService {

  Long countCountries();

  Country createCountry(Country country);

  Country updateCountry(Country country);

  Country deactivateCountry(String baseId);

  Country reactivateCountry(String baseId);

  Boolean deleteCountry(String baseId);

  Boolean isCountryActive(String baseId);

  Boolean isCountryExists(String baseId);

  Country getCountryByBaseId(String baseId);

  Region getRegionByCountryBaseId(String baseId);

  List<Country> getCountries();

  List<Country> getCountriesByRegionBaseId(String regionBaseId);
}
