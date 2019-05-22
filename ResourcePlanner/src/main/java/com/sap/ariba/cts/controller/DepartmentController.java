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

import com.sap.ariba.cts.model.dto.DepartmentDto;
import com.sap.ariba.cts.model.dto.RegionDto;
import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.service.impl.DepartmentServiceImpl;
import com.sap.ariba.cts.service.impl.RegionServiceImpl;
import com.sap.ariba.cts.utils.Constants;

import java.util.List;

/**
 * The type Department controller.
 */
@RestController
@RequestMapping(path = Constants.MASTER_DATA_URL,
        consumes = "application/json",
        produces = "application/json")
public class DepartmentController {

  private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);


  /**
   * The Region service.
   */
  @Autowired
  RegionServiceImpl regionService;

  /**
   * The Department service.
   */
  @Autowired
  DepartmentServiceImpl departmentService;


  @PostMapping(path = Constants.DEPARTMENT_URL)
  public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
    Department departmentCreated = departmentService.createDepartment(Department.toEntity(departmentDto));
    return new ResponseEntity<>(DepartmentDto.toDto(departmentCreated), HttpStatus.OK);
  }

  @PutMapping(path = Constants.DEPARTMENT_URL)
  public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto) {

    if (departmentService.isDepartmentExists(departmentDto.getBaseId())) {
      Department departmentUpdated = departmentService.updateDepartment(Department.toEntity(departmentDto));
      return new ResponseEntity<>(DepartmentDto.toDto(departmentUpdated), HttpStatus.OK);
    } else {
      return new ResponseEntity("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }


  @PatchMapping(path = Constants.DEPARTMENT_STATUS_SET_URL)
  public ResponseEntity<DepartmentDto> setStatusDepartment(@PathVariable(name = "deptBaseId", required = true) @NotBlank String deptBaseId,
                                            @PathVariable(name = "flag", required = true) boolean flag) {
    if (departmentService.isDepartmentExists(deptBaseId)){
      Department deptStatusUpd = null;
      if (!flag) {
        deptStatusUpd = departmentService.deactivateDepartment(deptBaseId);
        return new ResponseEntity<>(DepartmentDto.toDto(deptStatusUpd), HttpStatus.OK);
      } else {
        deptStatusUpd = departmentService.reactivateDepartment(deptBaseId);
        return new ResponseEntity<>(DepartmentDto.toDto(deptStatusUpd), HttpStatus.OK);
      }
    }
    return new ResponseEntity("Department NOT FOUND", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping(path = Constants.DEPARTMENT_CODE_URL)
  public ResponseEntity<Boolean> deleteDepartment(@PathVariable(name = "deptBaseId", required = true) @NotBlank String deptBaseId) {
    if (departmentService.isDepartmentExists(deptBaseId)) {
      return new ResponseEntity<>(departmentService.deleteDepartment(deptBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.DEPARTMENT_CODE_URL)
  public ResponseEntity<DepartmentDto> getDepartmentByBaseId(@PathVariable(name = "deptBaseId", required = true) @NotBlank String deptBaseId) {

    Department department = departmentService.getDepartmentByBaseId(deptBaseId);
    if (department != null) {
      return new ResponseEntity<>(DepartmentDto.toDto(department), HttpStatus.OK);
    } else {
      return new ResponseEntity("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }


  @GetMapping(path = Constants.DEPARTMENT_STATUS_GET_URL)
  public ResponseEntity<Boolean> isDepartmentActive(@PathVariable(name = "deptBaseId", required = true) @NotBlank String deptBaseId) {
    if (departmentService.isDepartmentExists(deptBaseId)) {
      return new ResponseEntity<>(departmentService.isDepartmentActive(deptBaseId), HttpStatus.OK);
    } else {
      return new ResponseEntity("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.DEPARTMENT_URL)
  public ResponseEntity<List<DepartmentDto>> getDepartments() {
    return new ResponseEntity<>(DepartmentDto.toDto(departmentService.getDepartments()), HttpStatus.OK);
  }


  @GetMapping(path = Constants.DEPARTMENT_REGION_GET_URL)
  public ResponseEntity<RegionDto> getRegionByDepartmentBaseID(@PathVariable(name = "deptBaseId", required = true) @NotBlank String deptBaseId) {
    if (departmentService.isDepartmentExists(deptBaseId)) {
      Region region = departmentService.getRegionByDeptBaseId(deptBaseId);
      return new ResponseEntity<>(RegionDto.toDto(region), HttpStatus.OK);
    }else {
      return new ResponseEntity("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.DEPARTMENTS_REGION_GET_URL)
  public ResponseEntity<List<DepartmentDto>> getDepartmentsByRegionBaseId(@PathVariable(name = "regionBaseId", required = true) @NotBlank String regionBaseId) {
    if (regionService.isRegionExists(regionBaseId)) {
      return new ResponseEntity<>(DepartmentDto.toDto(departmentService.getDepartmentsByRegionBaseId(regionBaseId)), HttpStatus.OK);
    } else {
      return new ResponseEntity("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = Constants.DEPARTMENT_COUNT_URL)
  public ResponseEntity getDepartmentCount() {
    return new ResponseEntity<>(departmentService.countDepartments(), HttpStatus.OK);
  }

}
