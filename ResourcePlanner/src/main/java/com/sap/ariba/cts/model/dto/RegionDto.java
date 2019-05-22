package com.sap.ariba.cts.model.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.Region;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionDto extends BaseDto {

  @NotEmpty
  @JsonProperty("code")
  String regionCode;

  @JsonProperty("name")
  String regionName;

  @JsonProperty("departments")
  List<DepartmentDto> departmentDtoList;

  @JsonProperty("countries")
  List<CountryDto> countryDtoList;

  public RegionDto() {
  }

  public RegionDto(String baseId, boolean isActive, String regionCode, String regionName) {
    super(baseId, isActive);
    this.regionCode = regionCode;
    this.regionName = regionName;
  }

  public String getRegionCode() {
    return regionCode;
  }

  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public List<DepartmentDto> getDepartmentDtoList() {
    return departmentDtoList;
  }

  public void setDepartmentDtoList(List<DepartmentDto> departmentDtoList) {
    this.departmentDtoList = departmentDtoList;
  }

  public List<CountryDto> getCountryDtoList() {
    return countryDtoList;
  }

  public void setCountryDtoList(List<CountryDto> countryDtoList) {
    this.countryDtoList = countryDtoList;
  }

  public static RegionDto toDto(Region region) {
    return new RegionDto(region.getBaseId(),
            region.isActive(),
            region.getRegionCode(),
            region.getRegionName());
  }

  public static List<RegionDto> toDto(List<Region> regions) {
    List<RegionDto> regionDtos = new ArrayList<>();

    for (Region region : regions) {
      regionDtos.add(RegionDto.toDto(region));
    }
    return regionDtos;
  }
}
