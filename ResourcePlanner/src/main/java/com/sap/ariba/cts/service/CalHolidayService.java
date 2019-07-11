package com.sap.ariba.cts.service;

import com.sap.ariba.cts.model.entity.CalHoliday;
import com.sap.ariba.cts.model.entity.Country;

import java.util.Date;
import java.util.List;

public interface CalHolidayService {


  Long countCalHolidays();

  CalHoliday createCalHoliday(CalHoliday calHoliday);


  CalHoliday updateCalHoliday(CalHoliday calHoliday);

  CalHoliday deactivateCalHoliday(String baseId);

  CalHoliday reactivateCalHoliday(String baseId);

  Boolean deleteCalHoliday(String baseId);


  Boolean isCalHolidayActive(String baseId);


  Boolean isCalHolidayExists(String baseId);

  Boolean isCalHolidayExistsByDate(Date cdate);


  CalHoliday getCalHolidayByBaseId(String baseId);

  CalHoliday getCalHolidayByDate(Date holDate);


  List<CalHoliday> getCalHolidays();

  List<CalHoliday> getCalHolidaysByCountry(Country country);

  List<CalHoliday> getCalHolidaysByYear(int year);
}
