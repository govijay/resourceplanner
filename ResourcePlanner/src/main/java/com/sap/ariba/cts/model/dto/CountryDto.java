package com.sap.ariba.cts.model.dto;

import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.City;
import com.sap.ariba.cts.model.entity.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryDto extends BaseDto {

  String ctryCode;
  String ctryName;
  List<CityDto> cityDtoList;
  RegionDto regionDto;

  public CountryDto() {
  }

  public CountryDto(String baseId, boolean isActive, String ctryCode, String ctryName, List<CityDto> cityDtoList, RegionDto regionDto) {
    super(baseId, isActive);
    this.ctryCode = ctryCode;
    this.ctryName = ctryName;
    this.cityDtoList = cityDtoList;
    this.regionDto = regionDto;
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

  public List<CityDto> getCityDtoList() {
    return cityDtoList;
  }

  public void setCityDtoList(List<CityDto> cityDtoList) {
    this.cityDtoList = cityDtoList;
  }

  public static CountryDto toDto(Country country) {
    return new CountryDto(country.getBaseId(),
            country.isActive(),
            country.getCountryCode(),
            country.getCountryName(),
            CityDto.toDto((List<City>) country.getCities()),
            RegionDto.toDto(country.getRegionCode())
      );
  }

  public static List<CountryDto> toDto(List<Country> countries) {
    List<CountryDto> countryDtos = new ArrayList<>();

    for (Country country : countries) {
      countryDtos.add(CountryDto.toDto(country));
    }
    return countryDtos;
  }
}
