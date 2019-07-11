package com.sap.ariba.cts.model.entity;

import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.CalHolidayDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "HOLIDAY")
@ClassMetaProperty(code = "H")
public class CalHoliday extends BaseEntity {

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
    int calYear;

    @Column(name = "NAME")
    String holName;

    @Column(name = "DATE")
    Date holDate;

    @OneToOne
    Country country;

    /**
     * Instantiates a new Base entity.
     */
    public CalHoliday() {
    }

    /**
     * Instantiates a new Base entity.
     *
     * @param active the active
     */
    public CalHoliday(boolean active, String baseId, int calYear, String holName, Date holDate, Country country) {
        super(active);
        this.baseId = baseId;
        this.calYear = calYear;
        this.holName = holName;
        this.holDate = holDate;
        this.country = country;
    }

    public static CalHoliday toEntity(CalHolidayDto calHolidayDto) {

        CalHoliday calHoliday = new CalHoliday(
                calHolidayDto.isActive(),
                calHolidayDto.getBaseId(),
                calHolidayDto.getCalYear(),
                calHolidayDto.getHolName(),
                calHolidayDto.getHolDate(),
                Country.toEntity(calHolidayDto.getCountry()));

        return calHoliday;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
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
}
