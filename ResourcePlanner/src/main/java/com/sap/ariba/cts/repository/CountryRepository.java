package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.Country;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * The interface Country repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@Repository
public interface CountryRepository<T, String extends Serializable> extends BaseEntityRepository<Country, String> {

  @Override
  @Query("select ctry from Country ctry where ctry.active=true")
  List<Country> getActiveEntity();

  /**
   * Gets country by base id.
   *
   * @param baseId the base id
   * @return the country by base id
   */
  Country getCountryByBaseId(String baseId);

  /**
   * Gets country by country code.
   *
   * @param countryCode the country code
   * @return the country by country code
   */
  Country getCountryByCountryCode(String countryCode);

  /**
   * Gets country by country name.
   *
   * @param countryName the country name
   * @return the country by country name
   */
  Country getCountryByCountryName(String countryName);

  /**
   * Gets countries by region base id.
   *
   * @param regionBaseId the region base id
   * @return the countries by region base id
   */
  Collection<Country> getCountriesByRegionBaseId(String regionBaseId);
}
