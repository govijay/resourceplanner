package com.sap.ariba.cts.repository;

import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.model.entity.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

    Country getCountryByCountryCode(String countryCode);


    /**
     * Gets countries by region base id.
     *
     * @param region the region base id
     * @return the countries by region base id
     */
    Collection<Country> getCountriesByRegion(Region region);
}
