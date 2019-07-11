package com.sap.ariba.cts.controller;

import com.sap.ariba.cts.model.dto.CityDto;
import com.sap.ariba.cts.model.dto.CountryDto;
import com.sap.ariba.cts.model.entity.City;
import com.sap.ariba.cts.model.entity.Country;
import com.sap.ariba.cts.service.impl.CityServiceImpl;
import com.sap.ariba.cts.service.impl.CountryServiceImpl;
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
 * The type city controller.
 */
@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CityController {

    private static Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    CountryServiceImpl countryService;

    @Autowired
    CityServiceImpl cityService;


    @PostMapping(path = Constants.CITY_URL)
    public ResponseEntity<CityDto> createCity(@Valid @RequestBody CityDto cityDto) {
        City cityCreated = cityService.createCity(City.toEntity(cityDto));
        return new ResponseEntity<>(CityDto.toDto(cityCreated), HttpStatus.OK);
    }

    @PutMapping(path = Constants.CITY_URL)
    public ResponseEntity<CityDto> updateCity(@Valid @RequestBody CityDto cityDto) {

        if (cityService.isCityExists(cityDto.getBaseId())) {
            City cityUpdated = cityService.updateCity(City.toEntity(cityDto));
            return new ResponseEntity<>(CityDto.toDto(cityUpdated), HttpStatus.OK);
        } else {
            return new ResponseEntity("CITY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping(path = Constants.CITY_STATUS_SET_URL)
    public ResponseEntity<CityDto> setStatusCity(@PathVariable(name = "cityBaseId", required = true) @NotBlank String cityBaseId,
                                                 @PathVariable(name = "flag", required = true) boolean flag) {
        if (cityService.isCityExists(cityBaseId)) {
            City cityStatusUpd = null;
            if (!flag) {
                cityStatusUpd = cityService.deactivateCity(cityBaseId);
                return new ResponseEntity<>(CityDto.toDto(cityStatusUpd), HttpStatus.OK);
            } else {
                cityStatusUpd = cityService.reactivateCity(cityBaseId);
                return new ResponseEntity<>(CityDto.toDto(cityStatusUpd), HttpStatus.OK);
            }
        }
        return new ResponseEntity("CITY NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = Constants.CITY_CODE_URL)
    public ResponseEntity<Boolean> deleteCity(@PathVariable(name = "cityBaseId", required = true) @NotBlank String cityBaseId) {
        if (cityService.isCityExists(cityBaseId)) {
            return new ResponseEntity<>(cityService.deleteCity(cityBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("CITY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CITY_CODE_URL)
    public ResponseEntity<CityDto> getCityByBaseId(@PathVariable(name = "cityBaseId", required = true) @NotBlank String cityBaseId) {

        City city = cityService.getCityByBaseId(cityBaseId);
        if (city != null) {
            return new ResponseEntity<>(CityDto.toDto(city), HttpStatus.OK);
        } else {
            return new ResponseEntity("CITY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = Constants.CITY_STATUS_GET_URL)
    public ResponseEntity<Boolean> isCityActive(@PathVariable(name = "cityBaseId", required = true) @NotBlank String cityBaseId) {
        if (cityService.isCityExists(cityBaseId)) {
            return new ResponseEntity<>(cityService.isCityActive(cityBaseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("CITY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CITY_URL)
    public ResponseEntity<List<CityDto>> getCities() {
        return new ResponseEntity<>(CityDto.toDto(cityService.getCities()), HttpStatus.OK);
    }


    @GetMapping(path = Constants.CITY_CTRY_GET_URL)
    public ResponseEntity<CountryDto> getCountryByCityBaseId(@PathVariable(name = "cityBaseId", required = true) @NotBlank String cityBaseId) {
        if (cityService.isCityExists(cityBaseId)) {
            Country country = cityService.getCountryByCityBaseId(cityBaseId);
            return new ResponseEntity<>(CountryDto.toDto(country), HttpStatus.OK);
        } else {
            return new ResponseEntity("CITY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CITYS_CTRY_GET_URL)
    public ResponseEntity<List<CityDto>> getCitiesByCountryBaseId(@PathVariable(name = "ctryBaseId", required = true) @NotBlank String ctryBaseId) {
        if (countryService.isCountryExists(ctryBaseId)) {
            return new ResponseEntity<>(CityDto.toDto(cityService.getCitiesByCountryBaseId(ctryBaseId)), HttpStatus.OK);
        } else {
            return new ResponseEntity("COUNTRY NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = Constants.CITY_COUNT_URL)
    public ResponseEntity getCityCount() {
        return new ResponseEntity<>(cityService.countCities(), HttpStatus.OK);
    }

}
