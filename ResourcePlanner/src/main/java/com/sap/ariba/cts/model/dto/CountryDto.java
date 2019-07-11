package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.model.entity.Region;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto extends BaseDto {

    String ctryCode;

    String ctryName;

    RegionDto regionDto;

    String regionBaseId;

    public CountryDto() {
    }

    public CountryDto(String baseId, boolean isActive, String ctryCode, String ctryName) {
        super(baseId, isActive);
        this.ctryCode = ctryCode;
        this.ctryName = ctryName;
    }

    public static CountryDto toDto(Country country) {

        CountryDto countryDto = new CountryDto(country.getBaseId(),
                country.isActive(),
                country.getCountryCode(),
                country.getCountryName());

        Region region = country.getRegion();

        if (region != null) {
            RegionDto regionDto = RegionDto.toDto(region);
            countryDto.setRegionDto(regionDto);
            countryDto.setRegionBaseId(region.getBaseId());
        }
        return countryDto;
    }

    public static List<CountryDto> toDto(List<Country> countries) {
        List<CountryDto> countryDtos = new ArrayList<>();

        for (Country country : countries) {
            countryDtos.add(CountryDto.toDto(country));
        }
        return countryDtos;
    }

    public String getCtryCode() {
        return ctryCode;
    }

    public void setCtryCode(String ctryCode) {
        this.ctryCode = ctryCode;
    }

    public String getCtryName() {
        return ctryName;
    }

    public void setCtryName(String ctryName) {
        this.ctryName = ctryName;
    }

    public RegionDto getRegionDto() {
        return regionDto;
    }

    public void setRegionDto(RegionDto regionDto) {
        this.regionDto = regionDto;
    }

    public String getRegionBaseId() {
        return regionBaseId;
    }

    public void setRegionBaseId(String regionBaseId) {
        this.regionBaseId = regionBaseId;
    }
}
