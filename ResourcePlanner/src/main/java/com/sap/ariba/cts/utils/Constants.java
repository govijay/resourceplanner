package com.sap.ariba.cts.utils;

public class Constants {

  // api url is /masterdata
  public static final String MASTER_DATA_URL = "/masterdata";
  // api url is /masterdata/regions
  public static final String REGION_URL = "/regions";
  // api url is /masterdata/regions/{regionCode}
  public static final String REGION_CODE_URL = REGION_URL + "/{regionBaseId}";
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
  // api url is /masterdata/departments/{deptBaseId}
  public static final String DEPARTMENT_CODE_URL = DEPARTMENT_URL + "/{deptBaseId}";
  // get department status(active or not)
  // api url is /masterdata/departments/{deptBaseId}/status
  public static final String DEPARTMENT_STATUS_GET_URL = DEPARTMENT_CODE_URL + "/status";
  // deactivate or reactivate department
  // api url is /masterdata/departments/{deptBaseId}/status/{flag}
  public static final String DEPARTMENT_STATUS_SET_URL = DEPARTMENT_CODE_URL + "/status" + "/{flag}";
  //get region of department
  // api url is /masterdata/departments/region/{deptBaseId}
  public static final String DEPARTMENT_REGION_GET_URL = DEPARTMENT_URL + "/region" + "/{deptBaseId}";
  // get departments of a region
  // api url is /masterdata/departments/regions/{regionBaseId}
  public static final String DEPARTMENTS_REGION_GET_URL = DEPARTMENT_URL + REGION_CODE_URL;
  // api url is /masterdata/regions/count
  public static final String DEPARTMENT_COUNT_URL = DEPARTMENT_URL + "/count";


  // create / update
  public static final String TEAM_URL = "/teams";

  // delete
  // api url is /masterdata/departments/{teamBaseId}
  public static final String TEAM_CODE_URL = TEAM_URL + "/{teamBaseId}";

  // deactivate or reactivate department
  // api url is /masterdata/teams/{teamBaseId}/status/{flag}
  public static final String TEAM_STATUS_SET_URL = TEAM_CODE_URL + "/status" + "/{flag}";

  // get department status(active or not)
  // api url is /masterdata/teams/{teamBaseId}/status
  public static final String TEAM_STATUS_GET_URL = TEAM_CODE_URL + "/status";

  //get department of team
  // api url is /masterdata/teams/department/{teamBaseId}
  public static final String TEAM_DEPT_GET_URL = DEPARTMENT_URL + "/department" + "/{teamBaseId}";

  // get teams of a department
  // api url is /masterdata/teams/regions/{deptBaseId}
  public static final String TEAMS_DEPT_GET_URL = TEAM_URL + DEPARTMENT_CODE_URL;

  // api url is /masterdata/teams/count
  public static final String TEAM_COUNT_URL = TEAM_URL + "/count";


  public static final String SUB_TEAM_URL = "/subteams";
  public static final String COUNTRY_URL = "/countries";
  public static final String CITY_URL = "/cities";

}
