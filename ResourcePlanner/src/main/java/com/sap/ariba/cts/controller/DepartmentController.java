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

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.service.impl.DepartmentServiceImpl;
import com.sap.ariba.cts.service.impl.RegionServiceImpl;
import com.sap.ariba.cts.utils.Constants;

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

  /**
   * Create department response entity.
   *
   * @param dept the dept
   * @return the response entity
   */
  @PostMapping(path = Constants.DEPARTMENT_URL)
  public ResponseEntity createDepartment(@Valid @RequestBody Department dept) {

    Region region = null;
    if (dept.getRegionBaseId() != null) {
      region = dept.getRegionBaseId();
    }

    if (!departmentService.isDepartmentActive(dept.getDepartCode(), region.getRegionCode())) {
      departmentService.createDepartment(dept);
      return new ResponseEntity<>("Department Created", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Department Exists. Use PUT Update Department", HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Update department response entity.
   *
   * @param dept the dept
   * @return the response entity
   */
  @PutMapping(path = Constants.DEPARTMENT_URL)
  public ResponseEntity updateDepartment(@Valid @RequestBody Department dept) {

    Region region = null;
    if (dept.getRegionBaseId() != null) {
      region = dept.getRegionBaseId();
    }
    if (departmentService.isDepartmentActive(dept.getDepartCode(), region.getRegionCode())) {
      departmentService.updateDepartment(dept);
      return new ResponseEntity<>("Department Updated", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Sets status department.
   *
   * @param regionCode the region code
   * @param deptCode   the dept code
   * @param flag       the flag
   * @return the status department
   */
// /masterdata/regions/{code}/departments/{deptcode}/status/{flag}
  @PatchMapping(path = Constants.DEPARTMENT_STATUS_SET_URL)
  public ResponseEntity setStatusDepartment(@PathVariable(name = "regionCode", required = true) @NotBlank String regionCode,
                                            @PathVariable(name = "deptCode", required = true) @NotBlank String deptCode,
                                            @PathVariable(name = "flag", required = true) boolean flag) {
    if (departmentService.isDepartmentActive(deptCode, regionCode) && !flag) {
      departmentService.deactivateDepartment(deptCode, regionCode);
      return new ResponseEntity<>("Department deactivated", HttpStatus.OK);
    } else if (!departmentService.isDepartmentActive(deptCode, regionCode) && flag) {
      departmentService.reactivateDepartment(deptCode, regionCode);
      return new ResponseEntity<>("Department reactivated", HttpStatus.OK);
    }
    return new ResponseEntity<>("Department NOT FOUND", HttpStatus.NOT_FOUND);
  }

  /**
   * Delete department response entity.
   *
   * @param regionCode the region code
   * @param deptCode   the dept code
   * @return the response entity
   */
  @DeleteMapping(path = Constants.DEPARTMENT_CODE_URL)
  public ResponseEntity deleteDepartment(@PathVariable(name = "regionCode", required = true) @NotBlank String regionCode,
                                         @PathVariable(name = "deptCode", required = true) @NotBlank String deptCode) {
    if (departmentService.getDepartmentByCode(deptCode, regionCode) != null) {
      departmentService.deleteDepartment(deptCode, regionCode);
      return new ResponseEntity<>("Department deleted", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets department name by code.
   *
   * @param regionCode the region code
   * @param deptCode   the dept code
   * @return the department name by code
   */
  @GetMapping(path = Constants.DEPARTMENT_CODE_URL)
  public ResponseEntity getDepartmentNameByCode(@PathVariable(name = "regionCode", required = true) @NotBlank String regionCode,
                                                @PathVariable(name = "deptCode", required = true) @NotBlank String deptCode) {

    Department department = departmentService.getDepartmentByCode(deptCode, regionCode);
    if (department != null) {
      return new ResponseEntity<>(department, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Is department active response entity.
   *
   * @param regionCode the region code
   * @param deptCode   the dept code
   * @return the response entity
   */
  @GetMapping(path = Constants.DEPARTMENT_STATUS_GET_URL)
  public ResponseEntity isDepartmentActive(@PathVariable(name = "regionCode", required = true) @NotBlank String regionCode,
                                           @PathVariable(name = "deptCode", required = true) @NotBlank String deptCode) {
    if (departmentService.getDepartmentByCode(deptCode, regionCode) != null) {
      return new ResponseEntity<>(departmentService.isDepartmentActive(deptCode, regionCode), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets departments.
   *
   * @return the departments
   */
  @GetMapping(path = Constants.DEPARTMENT_URL)
  public ResponseEntity getDepartments() {
    return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
  }

  /**
   * Gets region by department code.
   *
   * @param code the code
   * @return the region by department code
   */
  @GetMapping(path = Constants.REGION_DEPARTMENT_GET_URL)
  public ResponseEntity getRegionByDepartmentCode(@PathVariable(name = "deptCode", required = true) @NotBlank String code) {
    if (code != null) {
      return new ResponseEntity<>(departmentService.getRegionByDeptCode(code), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Department NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets departments by region code.
   *
   * @param code the code
   * @return the departments by region code
   */
  @GetMapping(path = Constants.DEPARTMENTS_REGION_GET_URL)
  public ResponseEntity getDepartmentsByRegionCode(@PathVariable(name = "regionCode", required = true) @NotBlank String code) {
    if (regionService.getRegionByCode(code) != null) {
      return new ResponseEntity<>(departmentService.getDepartmentByRegionCode(code), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Region NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets department count.
   *
   * @return the department count
   */
  @GetMapping(path = Constants.DEPARTMENT_COUNT_URL)
  public ResponseEntity getDepartmentCount() {
    return new ResponseEntity<>(departmentService.countDepartments(), HttpStatus.OK);
  }

}
