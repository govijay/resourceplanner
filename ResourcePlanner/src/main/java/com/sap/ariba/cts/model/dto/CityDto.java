package com.sap.ariba.cts.model.dto;

import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.City;

import java.util.ArrayList;
import java.util.List;

public class CityDto extends BaseDto {

  String ctyCode;
  String ctyName;
  CountryDto countryDto;

  public CityDto() {
  }

  public CityDto(String baseId, boolean isActive, String ctyCode, String ctyName, CountryDto countryDto) {
    super(baseId, isActive);
    this.ctyCode = ctyCode;
    this.ctyName = ctyName;
    this.countryDto = countryDto;
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

  public static CityDto toDto(City city) {
    return new CityDto(city.getBaseId(),
            city.isActive(),
            city.getCityCode(),
            city.getCityName(),
            CountryDto.toDto(city.getCountryBaseId()));
  }

  public static List<CityDto> toDto(List<City> cities) {
    List<CityDto> cityDtos = new ArrayList<>();

    for (City city : cities) {
      cityDtos.add(CityDto.toDto(city));
    }

    return cityDtos;
  }
}
