package com.sap.ariba.cts.repository;

import com.sap.ariba.cts.model.entity.CalHoliday;
import com.sap.ariba.cts.model.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository
public interface CalHolidayRepository<T, String extends Serializable> extends BaseEntityRepository<CalHoliday, String> {

    @Override
    @Query("select cal from CalHoliday cal where cal.active=true")
    List<CalHoliday> getActiveEntity();

    CalHoliday getCalHolidayByBaseId(String baseId);

    List<CalHoliday> getCalHolidayByCalYear(int calYear);

    List<CalHoliday> getCalHolidayByCountry(Country country);

    CalHoliday getCalHolidayByHolDate(Date holDate);
}
