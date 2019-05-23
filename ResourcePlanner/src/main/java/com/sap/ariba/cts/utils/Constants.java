package com.sap.ariba.cts.utils;

public final class Constants {

  // api url is /masterdata
  public static final String MASTER_DATA_URL = "/masterdata";

  // REGION API URL
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

  // DEPARTMENT API URL
  // create / update
  // api url is /masterdata/departments
  public static final String DEPARTMENT_URL = "/departments";
  // delete  / get by baseId
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

  // TEAM API URL
  // create / update
  public static final String TEAM_URL = "/teams";
  // delete  / get by baseId
  // api url is /masterdata/teams/{teamBaseId}
  public static final String TEAM_CODE_URL = TEAM_URL + "/{teamBaseId}";
  // deactivate or reactivate department
  // api url is /masterdata/teams/{teamBaseId}/status/{flag}
  public static final String TEAM_STATUS_SET_URL = TEAM_CODE_URL + "/status" + "/{flag}";
  // get department status(active or not)
  // api url is /masterdata/teams/{teamBaseId}/status
  public static final String TEAM_STATUS_GET_URL = TEAM_CODE_URL + "/status";
  //get department of team
  // api url is /masterdata/teams/department/{teamBaseId}
  public static final String TEAM_DEPT_GET_URL = TEAM_URL + "/department" + "/{teamBaseId}";
  // get teams of a department
  // api url is /masterdata/teams/departments/{deptBaseId}
  public static final String TEAMS_DEPT_GET_URL = TEAM_URL + DEPARTMENT_CODE_URL;
  // api url is /masterdata/teams/count
  public static final String TEAM_COUNT_URL = TEAM_URL + "/count";

  // SUB-TEAM API URL
  // create / update
  public static final String SUB_TEAM_URL = "/subteams";
  // delete / get by baseId
  // api url is /masterdata/subteams/{subTeamBaseId}
  public static final String SUB_TEAM_CODE_URL = SUB_TEAM_URL + "/{subTeamBaseId}";
  // deactivate or reactivate sub-team
  // api url is /masterdata/subteams/{subTeamBaseId}/status/{flag}
  public static final String SUB_TEAM_STATUS_SET_URL = SUB_TEAM_CODE_URL + "/status" + "/{flag}";
  // get sub-team status(active or not)
  // api url is /masterdata/subteams/{subTeamBaseId}/status
  public static final String SUB_TEAM_STATUS_GET_URL = SUB_TEAM_CODE_URL + "/status";
  //get team of sub-team
  // api url is /masterdata/subteams/team/{subTeamBaseId}
  public static final String SUB_TEAM_TEAM_GET_URL = "/team" + "/{subTeamBaseId}";
  // get sub-teams of a team
  // api url is /masterdata/subteams/teams/{teamBaseId}
  public static final String SUB_TEAMS_TEAM_GET_URL = SUB_TEAM_URL + TEAM_CODE_URL;
  // api url is /masterdata/subteams/count
  public static final String SUB_TEAM_COUNT_URL = SUB_TEAM_URL + "/count";

  // COUNTRY API URL
  // update /get all countries
  public static final String COUNTRY_URL = "/countries";
  // delete / get by baseId
  // api url is /masterdata/countries/{ctryBaseId}
  public static final String CTRY_CODE_URL = COUNTRY_URL + "/{ctryBaseId}";
  // deactivate or reactivate country
  // api url is /masterdata/countries/{ctryBaseId}/status/{flag}
  public static final String CTRY_STATUS_SET_URL = CTRY_CODE_URL + "/status" + "/{flag}";
  // get country status(active or not)
  // api url is /masterdata/countries/{ctryBaseId}/status
  public static final String CTRY_STATUS_GET_URL = CTRY_CODE_URL + "/status";
  // api url is /masterdata/countries/count
  public static final String CTRY_COUNT_URL = COUNTRY_URL + "/count";
  //get region of country
  // api url is /masterdata/countries/region/{ctryBaseId}
  public static final String CTRY_REGION_GET_URL = "/region" + "/{ctryBaseId}";
  // get countries of a region
  // api url is /masterdata/countries/regions/{regionCode}
  public static final String CTRYS_REGION_GET_URL = COUNTRY_URL + REGION_CODE_URL;

  // CITY API URL
  // update/ get all cities
  public static final String CITY_URL = "/cities";

}
