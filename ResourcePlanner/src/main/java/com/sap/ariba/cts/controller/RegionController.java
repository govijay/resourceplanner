package com.sap.ariba.cts.controller;

import com.sap.ariba.cts.model.dto.RegionDto;
import com.sap.ariba.cts.model.entity.Region;
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
 * The type Region controller.
 */
@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RegionController {

    private static Logger logger = LoggerFactory.getLogger(RegionController.class);

    /**
     * The Region service.
     */
    @Autowired
    RegionServiceImpl regionService;


    @PostMapping(path = Constants.REGION_URL)
    public ResponseEntity<RegionDto> createRegion(@Valid @RequestBody RegionDto regionDto) {
        Region regionCreated = regionService.createRegion(Region.toEntity(regionDto));
        return new ResponseEntity<>(RegionDto.toDto(regionCreated), HttpStatus.OK);
    }


    @PutMapping(path = Constants.REGION_URL)
    public ResponseEntity<RegionDto> updateRegion(@Valid @RequestBody RegionDto regionDto) {
        if (regionService.isRegionExists(regionDto.getBaseId())) {

            Region regionUpdated = regionService.updateRegion(Region.toEntity(regionDto));
            return new ResponseEntity<>(RegionDto.toDto(regionUpdated), HttpStatus.OK);
        } else {
            return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Sets status region.
     *
     * @param baseId the code
     * @param flag   the flag
     * @return the status region
     */
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

    /**
     * Delete region response entity.
     *
     * @param baseId the code
     * @return the response entity
     */
    @DeleteMapping(path = Constants.REGION_CODE_URL)
    public ResponseEntity<Boolean> deleteRegion(@PathVariable(name = "regionBaseId", required = true) @NotBlank String baseId) {
        if (regionService.isRegionExists(baseId)) {
            return new ResponseEntity<>(regionService.deleteRegion(baseId), HttpStatus.OK);
        } else {
            return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Gets region  by Id.
     *
     * @param baseId the code
     * @return the region name by code
     */
    @GetMapping(path = Constants.REGION_CODE_URL)
    public ResponseEntity<RegionDto> getRegionById(@PathVariable(name = "regionBaseId", required = true) @NotBlank String baseId) {
        if (regionService.isRegionExists(baseId)) {
            return new ResponseEntity<>(RegionDto.toDto(regionService.getRegionByBaseId(baseId)), HttpStatus.OK);
        } else {
            return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Is region active response entity.
     *
     * @param baseId the code
     * @return the response entity
     */
    @GetMapping(path = Constants.REGION_STATUS_GET_URL)
    public ResponseEntity isRegionActive(@PathVariable(name = "regionBaseId", required = true) @NotBlank String baseId) {
        if (regionService.isRegionExists(baseId)) {
            return new ResponseEntity<>(regionService.isRegionActive(baseId), HttpStatus.OK);
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
    public ResponseEntity<List<RegionDto>> getRegions() {
        return new ResponseEntity<>(RegionDto.toDto(regionService.getRegions()), HttpStatus.OK);
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
