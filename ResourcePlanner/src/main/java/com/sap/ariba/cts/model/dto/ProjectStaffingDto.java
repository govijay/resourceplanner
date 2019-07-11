package com.sap.ariba.cts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseDto;
import com.sap.ariba.cts.model.entity.ProjectStaffing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectStaffingDto extends BaseDto {

    private String customerName;

    private String salesOrder;

    private Date startDate;

    private Date endDate;

    private long estHours;

    private UserInfoDto staffOwner;

    private RegionDto region;

    private String scopeDesc;

    public ProjectStaffingDto() {
    }

    public ProjectStaffingDto(String baseId, boolean isActive, String customerName,
                              String salesOrder, Date startDate,
                              Date endDate, long estHours,
                              UserInfoDto staffOwner, RegionDto region,
                              String scopeDesc) {
        super(baseId, isActive);
        this.customerName = customerName;
        this.salesOrder = salesOrder;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estHours = estHours;
        this.staffOwner = staffOwner;
        this.region = region;
        this.scopeDesc = scopeDesc;
    }

    public static ProjectStaffingDto toDto(ProjectStaffing projStaffing) {

        ProjectStaffingDto projectStaffingDto = new ProjectStaffingDto(
                projStaffing.getBaseId(),
                projStaffing.isActive(),
                projStaffing.getCustomerName(),
                projStaffing.getSalesOrder(),
                projStaffing.getStartDate(),
                projStaffing.getEndDate(),
                projStaffing.getEstHours(),
                UserInfoDto.toDto(projStaffing.getStaffOwner()),
                RegionDto.toDto(projStaffing.getRegion()),
                projStaffing.getScopeDesc());

        return projectStaffingDto;
    }

    public static List<ProjectStaffingDto> toDto(List<ProjectStaffing> projectStaffings) {
        List<ProjectStaffingDto> projectStaffingDtos = new ArrayList<>();

        for (ProjectStaffing projectStaffing : projectStaffings) {
            projectStaffingDtos.add(ProjectStaffingDto.toDto(projectStaffing));
        }
        return projectStaffingDtos;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(String salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getEstHours() {
        return estHours;
    }

    public void setEstHours(long estHours) {
        this.estHours = estHours;
    }

    public UserInfoDto getStaffOwner() {
        return staffOwner;
    }

    public void setStaffOwner(UserInfoDto staffOwner) {
        this.staffOwner = staffOwner;
    }

    public RegionDto getRegion() {
        return region;
    }

    public void setRegion(RegionDto region) {
        this.region = region;
    }

    public String getScopeDesc() {
        return scopeDesc;
    }

    public void setScopeDesc(String scopeDesc) {
        this.scopeDesc = scopeDesc;
    }
}
