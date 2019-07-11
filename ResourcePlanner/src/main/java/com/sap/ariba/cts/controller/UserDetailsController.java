package com.sap.ariba.cts.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ariba.cts.model.dto.UserDetailsDto;
import com.sap.ariba.cts.model.entity.UserDetails;
import com.sap.ariba.cts.service.impl.UserDetailsServiceImpl;
import com.sap.ariba.cts.utils.Constants;

import java.util.List;

/**
 * The type user info controller.
 */
@RestController
@RequestMapping(path = Constants.USR_DETAILS_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserDetailsController {

  private static Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

  /**
   * The user info service.
   */
  @Autowired
  UserDetailsServiceImpl userDetailsService;


  @PostMapping
  public ResponseEntity<UserDetailsDto> createUserDetails(@Valid @RequestBody UserDetailsDto userDetailsDto) {
    UserDetails userDetails = userDetailsService.createUserDetails(UserDetails.toEntity(userDetailsDto));
    return new ResponseEntity<>(UserDetailsDto.toDto(userDetails), HttpStatus.OK);
  }

/*


  @PutMapping(path = Constants.REGION_URL)
  public ResponseEntity<RegionDto> updateRegion(@Valid @RequestBody RegionDto regionDto) {
    if (regionService.isRegionExists(regionDto.getBaseId())) {

      Region regionUpdated = regionService.updateRegion(Region.toEntity(regionDto));
      return new ResponseEntity<>(RegionDto.toDto(regionUpdated), HttpStatus.OK);
    } else {
      return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }



  @PatchMapping(path = Constants.REGION_STATUS_SET_URL)
  public ResponseEntity<RegionDto> setStatusRegion(@PathVariable(name = "regionBaseId", required = true) @NotBlank String baseId,
                                        @PathVariable(name = "flag", required = true) boolean flag) {

    Region region;
    if (regionService.isRegionActive(baseId) && !flag) {
      region = regionService.deactivateRegion(baseId);
      return new ResponseEntity<>(RegionDto.toDto(region), HttpStatus.OK);
    } else if (!regionService.isRegionActive(baseId) && flag) {
      region = regionService.reactivateRegion(baseId);
      return new ResponseEntity<>(RegionDto.toDto(region), HttpStatus.OK);
    }
    return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
  }



  @DeleteMapping(path = Constants.REGION_CODE_URL)
  public ResponseEntity<Boolean> deleteRegion(@PathVariable(name = "regionBaseId", required = true) @NotBlank String baseId) {
    if (regionService.isRegionExists(baseId)) {
      return new ResponseEntity<>(regionService.deleteRegion(baseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

*/


  @GetMapping(path = Constants.USR_DETAILS_CODE_URL)
  public ResponseEntity<UserDetailsDto> getUserDetailsByBaseId(@PathVariable(name = "usrdBaseId", required = true) @NotBlank String usrdBaseId) {
    if (userDetailsService.isUserDetailsExists(usrdBaseId)) {
      return new ResponseEntity<>(UserDetailsDto.toDto(userDetailsService.getUserDetailsByBaseId(usrdBaseId)), HttpStatus.OK);
    } else {
      return new ResponseEntity("USER DETAILS RECORD NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.USR_DETAILS_BY_USR_CODE_URL)
  public ResponseEntity<UserDetailsDto> getUserDetailsByUserBaseId(@PathVariable(name = "userBaseId", required = true) @NotBlank String userBaseId) {
    if (userDetailsService.isUserDetailsExistsForUser(userBaseId)) {
      return new ResponseEntity<>(UserDetailsDto.toDto(userDetailsService.getUserDetailsByUserId(userBaseId)), HttpStatus.OK);
    } else {
      return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets regions.
   *
   * @return the regions
   */
  @GetMapping
  public ResponseEntity<List<UserDetailsDto>> getUserDetails() {
    return new ResponseEntity<>(UserDetailsDto.toDto(userDetailsService.getUserDetails()), HttpStatus.OK);
  }

  /**
   * Gets regions count.
   *
   * @return the regions count
   */
  @GetMapping(path = Constants.USR_DETAILS_COUNT_URL)
  public ResponseEntity getUserDetailsCount() {
    return new ResponseEntity<>(userDetailsService.countUserDetails(), HttpStatus.OK);
  }


}
