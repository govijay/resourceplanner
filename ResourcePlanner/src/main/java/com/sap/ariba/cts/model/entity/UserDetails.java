package com.sap.ariba.cts.model.entity;

import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.UserDetailsDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "USER_DETAILS")
@ClassMetaProperty(code = "USRD")
public class UserDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usrDetailsSequenceGenerator")
    @GenericGenerator(name = "usrDetailsSequenceGenerator",
            strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
            parameters = {
                    @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "usrdet_seq"),
                    @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                    @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
            })
    @Column(name = "BASE_ID")
    private String baseId;

    @OneToOne
    private UserInfo userInfo;

    @Transient
    private String userId;

    @OneToOne
    private Region region;

    @OneToOne
    private Department department;

    @OneToOne
    private Team team;

    @OneToOne
    private SubTeam subTeam;

    @OneToOne
    private JobRole jobRole;

    @OneToOne
    private Country country;

    @OneToOne
    private City city;

    @OneToOne
    private UserInfo managerInfo;


    /**
     * Instantiates a new Base entity.
     */
    public UserDetails() {
    }

    /**
     * Instantiates a new Base entity.
     *
     * @param active the active
     */
    public UserDetails(boolean active, String baseId, String userId, UserInfo userInfo, Region region,
                       Department department, Team team, SubTeam subTeam,
                       Country country, City city, UserInfo managerInfo) {
        super(active);
        this.baseId = baseId;
        this.userId = userId;
        this.userInfo = userInfo;
        this.region = region;
        this.department = department;
        this.team = team;
        this.subTeam = subTeam;
        this.country = country;
        this.city = city;
        this.managerInfo = managerInfo;
    }

    public static UserDetails toEntity(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = new UserDetails(
                userDetailsDto.isActive(),
                userDetailsDto.getBaseId(),
                userDetailsDto.getUserId(),
                UserInfo.toEntity(userDetailsDto.getUserInfo()),
                Region.toEntity(userDetailsDto.getRegion()),
                Department.toEntity(userDetailsDto.getDepartment()),
                Team.toEntity(userDetailsDto.getTeam()),
                SubTeam.toEntity(userDetailsDto.getSubTeam()),
                Country.toEntity(userDetailsDto.getCountryDto()),
                City.toEntity(userDetailsDto.getCityDto()),
                UserInfo.toEntity(userDetailsDto.getManagerInfo()));

        return userDetails;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public SubTeam getSubTeam() {
        return subTeam;
    }

    public void setSubTeam(SubTeam subTeam) {
        this.subTeam = subTeam;
    }

    public JobRole getJobRole() {
        return jobRole;
    }

    public void setJobRole(JobRole jobRole) {
        this.jobRole = jobRole;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public UserInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(UserInfo managerInfo) {
        this.managerInfo = managerInfo;
    }

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist() {
        this.setActive(true);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "baseId='" + baseId + '\'' +
                ", userInfo=" + userInfo +
                ", region=" + region +
                ", department=" + department +
                ", team=" + team +
                ", subTeam=" + subTeam +
                '}';
    }

}
