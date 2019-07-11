package com.sap.ariba.cts.repository;

import com.sap.ariba.cts.model.entity.City;
import com.sap.ariba.cts.model.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

    City getCityByBaseId(String baseId);

    City getCityByCityCode(String cityCode);

    Collection<City> getCitiesByCountry(Country country);


}
