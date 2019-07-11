package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.City;
import com.sap.ariba.cts.model.entity.Country;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDto extends BaseDto {

  String ctyCode;

  String ctyName;

  CountryDto countryDto;

  String countryId;

  public CityDto() {
  }

  public CityDto(String baseId, boolean isActive, String ctyCode, String ctyName) {
    super(baseId, isActive);
    this.ctyCode = ctyCode;
    this.ctyName = ctyName;
  }

  public String getCtyCode() {
    return ctyCode;
  }

  public void setCtyCode(String ctyCode) {
    this.ctyCode = ctyCode;
  }

  public String getCtyName() {
    return ctyName;
  }

  public void setCtyName(String ctyName) {
    this.ctyName = ctyName;
  }

  public CountryDto getCountryDto() {
    return countryDto;
  }

  public void setCountryDto(CountryDto countryDto) {
    this.countryDto = countryDto;
  }

  public String getCountryId() {
    return countryId;
  }

  public void setCountryId(String countryId) {
    this.countryId = countryId;
  }

  public static CityDto toDto(City city) {
    CityDto cityDto = new CityDto(city.getBaseId(),
            city.isActive(),
            city.getCityCode(),
            city.getCityName());

    Country country = city.getCountry();
    if (country != null) {
      CountryDto countryDto = CountryDto.toDto(country);
      cityDto.setCountryDto(countryDto);
      cityDto.setCountryId(country.getBaseId());

    }
    return cityDto;
  }

  public static List<CityDto> toDto(List<City> cities) {
    List<CityDto> cityDtos = new ArrayList<>();

    for (City city : cities) {
      cityDtos.add(CityDto.toDto(city));
    }

    return cityDtos;
  }
}
