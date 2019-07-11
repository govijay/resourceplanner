package com.sap.ariba.cts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.ariba.cts.model.entity.CalHoliday;
import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.repository.CalHolidayRepository;
import com.sap.ariba.cts.service.CalHolidayService;
import com.sap.ariba.cts.utils.GenericUtil;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CalHolidayServiceImpl implements CalHolidayService {

  private static Logger logger = LoggerFactory.getLogger(CalHolidayServiceImpl.class);

  @Autowired
  private CalHolidayRepository calHolidayRepo;

  @Override
  public Long countCalHolidays() {
    return calHolidayRepo.count();
  }

  @Override
  public CalHoliday createCalHoliday(CalHoliday calHoliday) {
    calHolidayRepo.save(calHoliday);
    return (CalHoliday) calHolidayRepo.findById(calHoliday.getBaseId()).get();
  }

  @Override
  public CalHoliday updateCalHoliday(CalHoliday calHoliday) {
    CalHoliday calHolToUpdate = calHolidayRepo.getCalHolidayByBaseId(calHoliday.getBaseId());

    if (calHolToUpdate != null) {
      GenericUtil.copyNonNullProperties(calHoliday, calHolToUpdate);
      calHolidayRepo.save(calHolToUpdate);
    }
    return (CalHoliday) calHolidayRepo.findById(calHolToUpdate.getBaseId()).get();
  }

  @Override
  public CalHoliday deactivateCalHoliday(String baseId) {
    CalHoliday calHolToDeactivate = calHolidayRepo.getCalHolidayByBaseId(baseId);
    if (calHolToDeactivate != null) {
      calHolToDeactivate.setActive(false);
      calHolidayRepo.save(calHolToDeactivate);
    }
    return (CalHoliday) calHolidayRepo.findById(calHolToDeactivate.getBaseId()).get();
  }

  @Override
  public CalHoliday reactivateCalHoliday(String baseId) {
    CalHoliday calHolToReactivate = calHolidayRepo.getCalHolidayByBaseId(baseId);
    if (calHolToReactivate != null) {
      calHolToReactivate.setActive(true);
      calHolidayRepo.save(calHolToReactivate);
    }
    return (CalHoliday) calHolidayRepo.findById(calHolToReactivate.getBaseId()).get();
  }

  @Override
  public Boolean deleteCalHoliday(String baseId) {
    CalHoliday calHolidayToHardDelete = calHolidayRepo.getCalHolidayByBaseId(baseId);

    if (calHolidayToHardDelete != null) {
      calHolidayRepo.delete(calHolidayToHardDelete);
    }
    return true;
  }

  @Override
  public Boolean isCalHolidayActive(String baseId) {
    CalHoliday calHoliday = calHolidayRepo.getCalHolidayByBaseId(baseId);
    if (calHoliday != null) return calHoliday.isActive();
    return false;
  }

  @Override
  public Boolean isCalHolidayExists(String baseId) {
    CalHoliday calHoliday = calHolidayRepo.getCalHolidayByBaseId(baseId);
    if (calHoliday != null) return true;
    return false;
  }

  @Override
  public Boolean isCalHolidayExistsByDate(Date cdate) {
    CalHoliday calHoliday = calHolidayRepo.getCalHolidayByHolDate(cdate);
    if (calHoliday != null) return calHoliday.isActive();
    return false;
  }

  @Override
  public CalHoliday getCalHolidayByBaseId(String baseId) {
    return calHolidayRepo.getCalHolidayByBaseId(baseId);
  }

  @Override
  public CalHoliday getCalHolidayByDate(Date holDate) {
    return calHolidayRepo.getCalHolidayByHolDate(holDate);
  }

  @Override
  public List<CalHoliday> getCalHolidays() {
    return calHolidayRepo.getActiveEntity();
  }

  @Override
  public List<CalHoliday> getCalHolidaysByCountry(Country country) {
    return calHolidayRepo.getCalHolidayByCountry(country);
  }

  @Override
  public List<CalHoliday> getCalHolidaysByYear(int year) {
    return calHolidayRepo.getCalHolidayByCalYear(year);
  }
}
