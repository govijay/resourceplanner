package com.sap.ariba.cts.utils;

public class Constants {

    private Constants() {}
    
  // api url is /masterdata
  public static final String MASTER_DATA_URL = "/masterdata";
  // api url is /masterdata/regions
  public static final String REGION_URL = "/regions";
  // api url is /masterdata/regions/{regionCode}
  public static final String REGION_CODE_URL = REGION_URL + "/{regionCode}";
  // api url is /masterdata/regions/{regionCode}/status
  public static final String REGION_STATUS_GET_URL = REGION_CODE_URL + "/status";
  // api url is /masterdata/regions/{regionCode}/status/{flag}
  public static final String REGION_STATUS_SET_URL = REGION_CODE_URL + "/status" + "/{flag}";
  // api url is /masterdata/regions/count
  public static final String REGION_COUNT_URL = REGION_URL + "/count";


  // create / update
  // api url is /masterdata/departments
  public static final String DEPARTMENT_URL = "/departments";

  // delete
  // api url is /masterdata/regions/{regionCode}/departments/{deptcode}
  public static final String DEPARTMENT_CODE_URL = REGION_CODE_URL + DEPARTMENT_URL + "/{deptCode}";

  // get department status(active or not)
  // api url is /masterdata/regions/{regionCode}/departments/{deptcode}/status
  public static final String DEPARTMENT_STATUS_GET_URL = DEPARTMENT_CODE_URL + "/status";

  // deactivate or reactivate department
  // api url is /masterdata/regions/{regionCode}/departments/{deptcode}/status/{flag}
  public static final String DEPARTMENT_STATUS_SET_URL = DEPARTMENT_CODE_URL + "/status" + "/{flag}";

  //get region of department
  // api url is /masterdata/departments/{deptcode}
  public static final String REGION_DEPARTMENT_GET_URL = DEPARTMENT_URL + "/{deptCode}";

  // get departments of a region
  // api url is /masterdata/departments/regions/{regionCode}
  public static final String DEPARTMENTS_REGION_GET_URL = DEPARTMENT_URL + REGION_CODE_URL;
  // api url is /masterdata/regions/count
  public static final String DEPARTMENT_COUNT_URL = DEPARTMENT_URL + "/count";


  public static final String TEAM_URL = "/teams";
  public static final String SUB_TEAM_URL = "/subteams";
  public static final String COUNTRY_URL = "/countries";
  public static final String CITY_URL = "/cities";
  public static final String USERINFO = "/users";

}
