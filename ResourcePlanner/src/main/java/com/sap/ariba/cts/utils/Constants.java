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
    // delete / get by baseId
    // api url is /masterdata/cities/{cityBaseId}
    public static final String CITY_CODE_URL = CITY_URL + "/{cityBaseId}";
    // deactivate or reactivate city
    // api url is /masterdata/cities/{cityBaseId}/status/{flag}
    public static final String CITY_STATUS_SET_URL = CITY_CODE_URL + "/status" + "/{flag}";
    // get city status(active or not)
    // api url is /masterdata/cities/{cityBaseId}/status
    public static final String CITY_STATUS_GET_URL = CITY_CODE_URL + "/status";
    // api url is /masterdata/cities/count
    public static final String CITY_COUNT_URL = CITY_URL + "/count";
    //get country of city
    // api url is /masterdata/cities/country/{cityBaseId}
    public static final String CITY_CTRY_GET_URL = "/country" + "/{cityBaseId}";
    // get cities of a country
    // api url is /masterdata/cities/countries/{ctryBaseId}
    public static final String CITYS_CTRY_GET_URL = CITY_URL + CTRY_CODE_URL;
    // Job Role API URL
    public static final String JOB_ROLE_URL = "/jobroles";
    public static final String JOB_ROLE_CODE_URL = JOB_ROLE_URL + "/{jobRoleBaseId}";
    public static final String JOB_ROLE_STATUS_SET_URL = JOB_ROLE_CODE_URL + "/status" + "/{flag}";
    public static final String JOB_ROLE_STATUS_GET_URL = JOB_ROLE_CODE_URL + "/status";
    public static final String JOB_ROLE_COUNT_URL = JOB_ROLE_URL + "/count";
    // Calendar Holiday API URL
    public static final String CAL_HOLIDAYS_URL = "/calholidays";
    public static final String CAL_HOLIDAYS_CODE_URL = CAL_HOLIDAYS_URL + "/{calHolBaseId}";
    public static final String CAL_HOLIDAYS_STATUS_SET_URL = CAL_HOLIDAYS_CODE_URL + "/status" + "/{flag}";
    public static final String CAL_HOLIDAYS_STATUS_GET_URL = CAL_HOLIDAYS_CODE_URL + "/status";
    public static final String CAL_HOLIDAYS_COUNT_URL = CAL_HOLIDAYS_URL + "/count";
    // Project Staffing List API URL
    public static final String PROJECT_STAFFINGS_URL = "/pstaffings";
    public static final String PROJECT_STAFFINGS_CODE_URL = PROJECT_STAFFINGS_URL + "/{projStaffBaseId}";
    public static final String PROJECT_STAFFINGS_BY_CUS_URL = PROJECT_STAFFINGS_URL + "/{cusName}";
    public static final String PROJECT_STAFFINGS_BY_REG_URL = PROJECT_STAFFINGS_URL + "/{regBaseId}";
    public static final String PROJECT_STAFFINGS_STATUS_SET_URL = PROJECT_STAFFINGS_CODE_URL + "/status" + "/{flag}";
    public static final String PROJECT_STAFFINGS_STATUS_GET_URL = PROJECT_STAFFINGS_CODE_URL + "/status";
    public static final String PROJECT_STAFFINGS_COUNT_URL = PROJECT_STAFFINGS_URL + "/count";
    // USER DETAILS API URL
    // create/ update user details
    public static final String USR_DETAILS_URL = "/userdetails";
    // delete / get by baseId
    // api url is /userdetails/{usrdBaseId}
    public static final String USR_DETAILS_CODE_URL = "/{usrdBaseId}";
    // api url is /userdetails/user/{userBaseId}
    public static final String USR_DETAILS_BY_USR_CODE_URL = "/user" + "/{userBaseId}";
    // api url is /masterdata/cities/count
    public static final String USR_DETAILS_COUNT_URL = USR_DETAILS_URL + "/count";
    public static final String USERINFO = "/users";
    public static final String UPS_DETAILS_URL = "/upschedules";


    // USER PROJECT SCHEDULE  API URL
    public static final String UPS_CODE_URL = "/{upscheduleBaseId}";

    private Constants() {
    }


}
