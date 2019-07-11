package com.sap.ariba.cts.controller;

import com.sap.ariba.cts.model.dto.CountryDto;
import com.sap.ariba.cts.model.dto.RegionDto;
import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.service.impl.CountryServiceImpl;
import com.sap.ariba.cts.service.impl.RegionServiceImpl;
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

/**
 * The type Country controller.
 */
@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CountryController {

    private static Logger logger = LoggerFactory.getLogger(CountryController.class);


    /**
     * The Region service.
     */
    @Autowired
    RegionServiceImpl regionService;

    /**
     * The Department service.
     */
    @Autowired
    CountryServiceImpl countryService;


    @PostMapping(path = Constants.COUNTRY_URL)
    public ResponseEntity<CountryDto> createCountry(@Valid @RequestBody CountryDto countryDto) {
        Country ctryCreated = countryService.createCountry(Country.toEntity(countryDto));
        return new ResponseEntity<>(CountryDto.toDto(ctryCreated), HttpStatus.OK);
    }

    @PutMapping(path = Constants.COUNTRY_URL)
    public ResponseEntity<CountryDto> updateCountry(@Valid @RequestBody CountryDto countryDto) {

        if (countryService.isCountryExists(countryDto.getBaseId())) {
            Country ctryUpdated = countryService.updateCountry(Country.toEntity(countryDto));
            return new ResponseEntity<>(CountryDto.toDto(ctryUpdated), HttpStatus.OK);
        } else {
            return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping(path = Constants.CTRY_STATUS_SET_URL)
    public ResponseEntity<CountryDto> setStatusCountry(@PathVariable(name = "ctryBaseId", required = true) @NotBlank String ctryBaseId,
                                                       @PathVariable(name = "flag", required = true) boolean flag) {
        if (countryService.isCountryExists(ctryBaseId)) {
            Country ctryStatusUpd = null;
            if (!flag) {
                ctryStatusUpd = countryService.deactivateCountry(ctryBaseId);
                return new ResponseEntity<>(CountryDto.toDto(ctryStatusUpd), HttpStatus.OK);
            } else {
                ctryStatusUpd = countryService.reactivateCountry(ctryBaseId);
                return new ResponseEntity<>(CountryDto.toDto(ctryStatusUpd), HttpStatus.OK);
            }
        }
        return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = Constants.CTRY_CODE_URL)
    public ResponseEntity<Boolean> deleteCountry(@PathVariable(name = "ctryBaseId", required = true) @NotBlank String ctryBaseId) {
        if (countryService.isCountryExists(ctryBaseId)) {
            return new ResponseEntity<>(countryService.deleteCountry(ctryBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CTRY_CODE_URL)
    public ResponseEntity<CountryDto> getCountryByBaseId(@PathVariable(name = "ctryBaseId", required = true) @NotBlank String ctryBaseId) {

        Country country = countryService.getCountryByBaseId(ctryBaseId);
        if (country != null) {
            return new ResponseEntity<>(CountryDto.toDto(country), HttpStatus.OK);
        } else {
            return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = Constants.CTRY_STATUS_GET_URL)
    public ResponseEntity<Boolean> isCountryActive(@PathVariable(name = "ctryBaseId", required = true) @NotBlank String ctryBaseId) {
        if (countryService.isCountryExists(ctryBaseId)) {
            return new ResponseEntity<>(countryService.isCountryActive(ctryBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.COUNTRY_URL)
    public ResponseEntity<List<CountryDto>> getCountries() {
        return new ResponseEntity<>(CountryDto.toDto(countryService.getCountries()), HttpStatus.OK);
    }


    @GetMapping(path = Constants.CTRY_REGION_GET_URL)
    public ResponseEntity<RegionDto> getRegionByCountryBaseId(@PathVariable(name = "ctryBaseId", required = true) @NotBlank String ctryBaseId) {
        if (countryService.isCountryExists(ctryBaseId)) {
            Region region = countryService.getRegionByCountryBaseId(ctryBaseId);
            return new ResponseEntity<>(RegionDto.toDto(region), HttpStatus.OK);
        } else {
            return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CTRYS_REGION_GET_URL)
    public ResponseEntity<List<CountryDto>> getCountriesByRegionBaseId(@PathVariable(name = "regionBaseId", required = true) @NotBlank String regionBaseId) {
        if (regionService.isRegionExists(regionBaseId)) {
            return new ResponseEntity<>(CountryDto.toDto(countryService.getCountriesByRegionBaseId(regionBaseId)), HttpStatus.OK);
        } else {
            return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CTRY_COUNT_URL)
    public ResponseEntity getCountryCount() {
        return new ResponseEntity<>(countryService.countCountries(), HttpStatus.OK);
    }

}
