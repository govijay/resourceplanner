package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.CalendarHolidayDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

import java.util.Date;


@Entity
@Table(name = "HOLIDAY")
@ClassMetaProperty(code = "H")
public class CalendarHoliday extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holidaySequenceGenerator")
  @GenericGenerator(name = "holidaySequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "holiday_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  String baseId;

  @Column(name = "YEAR")
  long calYear;

  @Column(name = "NAME")
  String holName;

  @Column(name = "DATE")
  Date holDate;

  @Column(name = "COUNTRY")
  Country country;

  /**
   * Instantiates a new Base entity.
   */
  public CalendarHoliday() {
  }

  /**
   * Instantiates a new Base entity.
   *
   * @param active the active
   */
  public CalendarHoliday(boolean active,String baseId, long calYear, String holName, Date holDate, Country country) {
    super(active);
    this.baseId = baseId;
    this.calYear = calYear;
    this.holName = holName;
    this.holDate = holDate;
    this.country = country;
  }

  public String getBaseId() {
    return baseId;
  }

  public void setBaseId(String baseId) {
    this.baseId = baseId;
  }

  public long getCalYear() {
    return calYear;
  }

  public void setCalYear(long calYear) {
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

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  /**
   * Pre persist.
   */
  @PrePersist
  public void prePersist() {
    this.setActive(true);
  }

  public static CalendarHoliday toEntity(CalendarHolidayDto calendarHolidayDto) {

    CalendarHoliday calendarHoliday = new CalendarHoliday(
            calendarHolidayDto.isActive(),
            calendarHolidayDto.getBaseId(),
            calendarHolidayDto.getCalYear(),
            calendarHolidayDto.getHolName(),
            calendarHolidayDto.getHolDate(),
            Country.toEntity(calendarHolidayDto.getCountry()));

    return calendarHoliday;
  }
}
