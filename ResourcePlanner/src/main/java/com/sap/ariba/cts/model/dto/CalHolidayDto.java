package com.sap.ariba.cts.model.dto;

import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.CalHoliday;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalHolidayDto extends BaseDto {

  int calYear;

  String holName;

  Date holDate;

  CountryDto country;

  public CalHolidayDto() {
  }

  public CalHolidayDto(String baseId, boolean isActive, int calYear, String holName, Date holDate, CountryDto country) {
    super(baseId, isActive);
    this.calYear = calYear;
    this.holName = holName;
    this.holDate = holDate;
    this.country = country;
  }

  public int getCalYear() {
    return calYear;
  }

  public void setCalYear(int calYear) {
    this.calYear = calYear;
  }

  public String getHolName() {
    return holName;
  }

  public void setHolName(String holName) {
    this.holName = holName;
  }

  public Date getHolDate() {
    return holDate;
  }

  public void setHolDate(Date holDate) {
    this.holDate = holDate;
  }

  public CountryDto getCountry() {
    return country;
  }

  public void setCountry(CountryDto country) {
    this.country = country;
  }

  public static CalHolidayDto toDto(CalHoliday calHoliday) {
    return new CalHolidayDto(calHoliday.getBaseId(),
            calHoliday.isActive(),
            calHoliday.getCalYear(),
            calHoliday.getHolName(),
            calHoliday.getHolDate(),
            CountryDto.toDto(calHoliday.getCountry()));
  }

  public static List<CalHolidayDto> toDto(List<CalHoliday> calHolidays) {
    List<CalHolidayDto> calHolidayDtos = new ArrayList<>();

    for (CalHoliday calHoliday : calHolidays) {
      calHolidayDtos.add(CalHolidayDto.toDto(calHoliday));
    }
    return calHolidayDtos;
  }
}
