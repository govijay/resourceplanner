package com.sap.ariba.cts.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.CountryDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Country Model.
 *
 * <p> Country Entity Class</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "COUNTRIES")
@ClassMetaProperty(code = "CTRY")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Country extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ctrySequenceGenerator")
    @GenericGenerator(name = "ctrySequenceGenerator",
            strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
            parameters = {
                    @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "ctry_seq"),
                    @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                    @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
            })
    @Column(name = "BASE_ID")
    private String baseId;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "REGION_BASEID")
    @JsonBackReference
    private Region region;

    @Transient
    private String regionBaseId;

    @OneToMany(mappedBy = "country")
    @JsonManagedReference
    private Collection<City> cities;

    /**
     * Instantiates a new Country.
     */
    protected Country() {
    }

    /**
     * Instantiates a new Base entity.
     *
     * @param active the active
     */
    public Country(boolean active, String baseId, String countryCode, String countryName, String regionBaseId) {
        super(active);
        this.baseId = baseId;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionBaseId = regionBaseId;
    }

    public static Country toEntity(CountryDto countryDto) {
        return new Country(
                countryDto.isActive(),
                countryDto.getBaseId(),
                countryDto.getCtryCode(),
                countryDto.getCtryName(),
                countryDto.getRegionBaseId());

    }

    /**
     * Gets country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets country code.
     *
     * @param countryCode the country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Gets country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets country name.
     *
     * @param countryName the country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Gets base id.
     *
     * @return the base id
     */
    public String getBaseId() {
        return baseId;
    }

    /**
     * Sets base id.
     *
     * @param baseId the base id
     */
    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    /**
     * Gets cities.
     *
     * @return the cities
     */
    public Collection<City> getCities() {
        return cities;
    }

    /**
     * Sets cities.
     *
     * @param cities the cities
     */
    public void setCities(Collection<City> cities) {
        this.cities = cities;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getRegionBaseId() {
        return regionBaseId;
    }

    public void setRegionBaseId(String regionBaseId) {
        this.regionBaseId = regionBaseId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "baseId='" + baseId + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", regionBaseId=" + regionBaseId +
                '}';
    }
}
