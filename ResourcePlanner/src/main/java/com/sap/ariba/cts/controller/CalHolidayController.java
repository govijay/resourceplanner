package com.sap.ariba.cts.controller;

import com.sap.ariba.cts.model.dto.CalHolidayDto;
import com.sap.ariba.cts.model.entity.CalHoliday;
import com.sap.ariba.cts.service.impl.CalHolidayServiceImpl;
import com.sap.ariba.cts.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CalHolidayController {

    private static Logger logger = LoggerFactory.getLogger(CalHolidayController.class);

    @Autowired
    private CalHolidayServiceImpl calHolidayService;


    @PostMapping(path = Constants.CAL_HOLIDAYS_URL)
    public ResponseEntity<CalHolidayDto> createCalHoliday(@Valid @RequestBody CalHolidayDto calHolidayDto) {
        CalHoliday calHolidayCreated = calHolidayService.createCalHoliday(CalHoliday.toEntity(calHolidayDto));
        return new ResponseEntity<>(CalHolidayDto.toDto(calHolidayCreated), HttpStatus.OK);
    }

    @PutMapping(path = Constants.CAL_HOLIDAYS_URL)
    public ResponseEntity<CalHolidayDto> updateCalHoliday(@Valid @RequestBody CalHolidayDto calHolidayDto) {

        if (calHolidayService.isCalHolidayExists(calHolidayDto.getBaseId())) {
            CalHoliday calHolidayUpdated = calHolidayService.updateCalHoliday(CalHoliday.toEntity(calHolidayDto));
            return new ResponseEntity<>(CalHolidayDto.toDto(calHolidayUpdated), HttpStatus.OK);
        } else {
            return new ResponseEntity("CAL HOLIDAY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping(path = Constants.CAL_HOLIDAYS_STATUS_SET_URL)
    public ResponseEntity<CalHolidayDto> setStatusCalHoliday(@PathVariable(name = "calHolidayBaseId", required = true) @NotBlank String calHolidayBaseId,
                                                             @PathVariable(name = "flag", required = true) boolean flag) {
        if (calHolidayService.isCalHolidayExists(calHolidayBaseId)) {
            CalHoliday calHolidayStatusUpd = null;
            if (!flag) {
                calHolidayStatusUpd = calHolidayService.deactivateCalHoliday(calHolidayBaseId);
                return new ResponseEntity<>(CalHolidayDto.toDto(calHolidayStatusUpd), HttpStatus.OK);
            } else {
                calHolidayStatusUpd = calHolidayService.reactivateCalHoliday(calHolidayBaseId);
                return new ResponseEntity<>(CalHolidayDto.toDto(calHolidayStatusUpd), HttpStatus.OK);
            }
        }
        return new ResponseEntity("CAL HOLIDAY  NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = Constants.CAL_HOLIDAYS_CODE_URL)
    public ResponseEntity<Boolean> deleteCalHoliday(@PathVariable(name = "calHolidayBaseId", required = true) @NotBlank String calHolidayBaseId) {
        if (calHolidayService.isCalHolidayExists(calHolidayBaseId)) {
            return new ResponseEntity<>(calHolidayService.deleteCalHoliday(calHolidayBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("CAL HOLIDAY  NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CAL_HOLIDAYS_CODE_URL)
    public ResponseEntity<CalHolidayDto> getCalHolidayByBaseId(@PathVariable(name = "calHolidayBaseId", required = true) @NotBlank String calHolidayBaseId) {

        CalHoliday calHoliday = calHolidayService.getCalHolidayByBaseId(calHolidayBaseId);
        if (calHoliday != null) {
            return new ResponseEntity<>(CalHolidayDto.toDto(calHoliday), HttpStatus.OK);
        } else {
            return new ResponseEntity("CAL HOLIDAY  NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = Constants.CAL_HOLIDAYS_STATUS_GET_URL)
    public ResponseEntity<Boolean> isCalHolidayActive(@PathVariable(name = "calHolidayBaseId", required = true) @NotBlank String calHolidayBaseId) {
        if (calHolidayService.isCalHolidayExists(calHolidayBaseId)) {
            return new ResponseEntity<>(calHolidayService.isCalHolidayActive(calHolidayBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("CAL HOLIDAY  NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CAL_HOLIDAYS_URL)
    public ResponseEntity<List<CalHolidayDto>> getCalHolidays() {
        return new ResponseEntity<>(CalHolidayDto.toDto(calHolidayService.getCalHolidays()), HttpStatus.OK);
    }


    @GetMapping(path = Constants.CAL_HOLIDAYS_COUNT_URL)
    public ResponseEntity getCalHolidaysCount() {
        return new ResponseEntity<>(calHolidayService.countCalHolidays(), HttpStatus.OK);
    }

}
