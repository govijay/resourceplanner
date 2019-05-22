package com.sap.ariba.cts.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.service.impl.RegionServiceImpl;
import com.sap.ariba.cts.utils.Constants;

/**
 * The type Region controller.
 */
@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = "application/json",
        produces = "application/json")
public class RegionController {

  private static Logger logger = LoggerFactory.getLogger(RegionController.class);

  /**
   * The Region service.
   */
  @Autowired
  RegionServiceImpl regionService;

  /**
   * Create region response entity.
   *
   * @param region the region
   * @return the response entity
   */
  @PostMapping(path = Constants.REGION_URL)
  public ResponseEntity createRegion(@Valid @RequestBody Region region) {

    if (!regionService.isRegionExists(region.getRegionCode())) {
      regionService.createRegion(region);
      return new ResponseEntity<>("Region Created", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Region Exists. Use PUT Update Region", HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Update region response entity.
   *
   * @param region the region
   * @return the response entity
   */
  @PutMapping(path = Constants.REGION_URL)
  public ResponseEntity updateRegion(@Valid @RequestBody Region region) {
    if (regionService.isRegionActive(region.getRegionCode())) {
      regionService.updateRegion(region);
      return new ResponseEntity<>("Region Updated", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Sets status region.
   *
   * @param code the code
   * @param flag the flag
   * @return the status region
   */
  @PatchMapping(path = Constants.REGION_STATUS_SET_URL)
  public ResponseEntity setStatusRegion(@PathVariable(name = "regionCode", required = true) @NotBlank String code,
                                        @PathVariable(name = "flag", required = true) boolean flag) {
    if (regionService.isRegionActive(code) && !flag) {
      regionService.deactivateRegion(code);
      return new ResponseEntity<>("Region deactivated", HttpStatus.OK);
    } else if (!regionService.isRegionActive(code) && flag) {
      regionService.reactivateRegion(code);
      return new ResponseEntity<>("Region reactivated", HttpStatus.OK);
    }
    return new ResponseEntity<>("Region NOT FOUND", HttpStatus.NOT_FOUND);
  }

  /**
   * Delete region response entity.
   *
   * @param code the code
   * @return the response entity
   */
  @DeleteMapping(path = Constants.REGION_CODE_URL)
  public ResponseEntity deleteRegion(@PathVariable(name = "regionCode", required = true) @NotBlank String code) {
    if (regionService.getRegionByCode(code) != null) {
      regionService.deleteRegion(code);
      return new ResponseEntity<>("Region deleted", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets region name by code.
   *
   * @param code the code
   * @return the region name by code
   */
  @GetMapping(path = Constants.REGION_CODE_URL)
  public ResponseEntity getRegionNameByCode(@PathVariable(name = "regionCode", required = true) @NotBlank String code) {
    if (regionService.getRegionByCode(code) != null) {
      return new ResponseEntity<>(regionService.getRegionNameByRegionCode(code), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Is region active response entity.
   *
   * @param code the code
   * @return the response entity
   */
  @GetMapping(path = Constants.REGION_STATUS_GET_URL)
  public ResponseEntity isRegionActive(@PathVariable(name = "regionCode", required = true) @NotBlank String code) {
    if (regionService.getRegionByCode(code) != null) {
      return new ResponseEntity<>(regionService.isRegionActive(code), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets regions.
   *
   * @return the regions
   */
  @GetMapping(path = Constants.REGION_URL)
  public ResponseEntity getRegions() {
    return new ResponseEntity<>(regionService.getRegions(), HttpStatus.OK);
  }

  /**
   * Gets regions count.
   *
   * @return the regions count
   */
  @GetMapping(path = Constants.REGION_COUNT_URL)
  public ResponseEntity getRegionsCount() {
    return new ResponseEntity<>(regionService.countRegions(), HttpStatus.OK);
  }


}
