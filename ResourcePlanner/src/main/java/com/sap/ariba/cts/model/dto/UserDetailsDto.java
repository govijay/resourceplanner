package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.UserDetails;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDto extends BaseDto {

    String userId;

    UserInfoDto userInfo;

    RegionDto region;

    DepartmentDto department;

    TeamDto team;

    SubTeamDto subTeam;

    CountryDto countryDto;

    CityDto cityDto;

    UserInfoDto managerInfo;

    public UserDetailsDto() {
    }

    public UserDetailsDto(String baseId, boolean isActive, String userId, UserInfoDto userInfo,
                          RegionDto region, DepartmentDto department, TeamDto team,
                          SubTeamDto subTeam, CountryDto countryDto, CityDto cityDto,
                          UserInfoDto managerInfo) {
        super(baseId, isActive);
        this.userId = userId;
        this.userInfo = userInfo;
        this.region = region;
        this.department = department;
        this.team = team;
        this.subTeam = subTeam;
        this.countryDto = countryDto;
        this.cityDto = cityDto;
        this.managerInfo = managerInfo;
    }

    public static UserDetailsDto toDto(UserDetails userDetails) {

        UserDetailsDto userDetailsDto = new UserDetailsDto(userDetails.getBaseId(),
                userDetails.isActive(),
                userDetails.getUserId(),
                UserInfoDto.toDto(userDetails.getUserInfo()),
                RegionDto.toDto(userDetails.getRegion()),
                DepartmentDto.toDto(userDetails.getDepartment()),
                TeamDto.toDto(userDetails.getTeam()),
                SubTeamDto.toDto(userDetails.getSubTeam()),
                CountryDto.toDto(userDetails.getCountry()),
                CityDto.toDto(userDetails.getCity()),
                UserInfoDto.toDto(userDetails.getManagerInfo())
        );

        return userDetailsDto;
    }

    public static List<UserDetailsDto> toDto(List<UserDetails> userDetails) {
        List<UserDetailsDto> userDetailsDtos = new ArrayList<>();

        for (UserDetails userDetail : userDetails) {
            userDetailsDtos.add(UserDetailsDto.toDto(userDetail));
        }
        return userDetailsDtos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RegionDto getRegion() {
        return region;
    }

    public void setRegion(RegionDto region) {
        this.region = region;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public SubTeamDto getSubTeam() {
        return subTeam;
    }

    public void setSubTeam(SubTeamDto subTeam) {
        this.subTeam = subTeam;
    }

    public UserInfoDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDto userInfo) {
        this.userInfo = userInfo;
    }

    public CountryDto getCountryDto() {
        return countryDto;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }

    public CityDto getCityDto() {
        return cityDto;
    }

    public void setCityDto(CityDto cityDto) {
        this.cityDto = cityDto;
    }

    public UserInfoDto getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(UserInfoDto managerInfo) {
        this.managerInfo = managerInfo;
    }
}
