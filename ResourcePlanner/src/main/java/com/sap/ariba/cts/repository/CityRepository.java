package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.City;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * The interface City repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@Repository
public interface CityRepository<T, String extends Serializable> extends BaseEntityRepository<City, String> {

  @Override
  @Query("select c from City c where c.active=true")
  List<City> getActiveEntity();

  /**
   * Gets city by base id.
   *
   * @param baseId the base id
   * @return the city by base id
   */
  City getCityByBaseId(String baseId);

  /**
   * Gets city by city code.
   *
   * @param cityCode the city code
   * @return the city by city code
   */
  City getCityByCityCode(String cityCode);

  /**
   * Gets city by city name.
   *
   * @param cityName the city name
   * @return the city by city name
   */
  City getCityByCityName(String cityName);

  /**
   * Gets cities by country base id.
   *
   * @param countryBaseId the country base id
   * @return the cities by country base id
   */
  Collection<City> getCitiesByCountryBaseId(String countryBaseId);


}
