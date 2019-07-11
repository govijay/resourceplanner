package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.ProjectStaffingDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

import java.util.Date;

@Entity
@Table(name = "PROJECT_STAFFING")
@ClassMetaProperty(code = "PRST")
public class ProjectStaffing extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proStaffSequenceGenerator")
  @GenericGenerator(name = "proStaffSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "prst_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "CUST_NAME")
  private String customerName;

  @Column(name = "SALES_ORDER")
  private String salesOrder;

  @Column(name = "PROJ_STARTDATE")
  private Date startDate;

  @Column(name = "PROJ_ENDDATE")
  private Date endDate;

  @Column(name = "EST_HRS")
  private long estHours;

  @OneToOne
  private UserInfo staffOwner;

  @OneToOne
  private Region region;

  @Column(name = "SCOPEDESC")
  private String scopeDesc;

  /**
   * Instantiates a new Base entity.
   */
  public ProjectStaffing() {
  }

  /**
   * Instantiates a new Base entity.
   *
   * @param active the active
   */
  public ProjectStaffing(boolean active,String baseId, String customerName,
                         String salesOrder, Date startDate,
                         Date endDate, long estHours, UserInfo staffOwner, Region region,
                         String scopeDesc) {
    super(active);
    this.baseId = baseId;
    this.customerName = customerName;
    this.salesOrder = salesOrder;
    this.startDate = startDate;
    this.endDate = endDate;
    this.estHours = estHours;
    this.staffOwner = staffOwner;
    this.region = region;
    this.scopeDesc = scopeDesc;
  }

  public String getBaseId() {
    return baseId;
  }

  public void setBaseId(String baseId) {
    this.baseId = baseId;
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

  public UserInfo getStaffOwner() {
    return staffOwner;
  }

  public void setStaffOwner(UserInfo staffOwner) {
    this.staffOwner = staffOwner;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public String getScopeDesc() {
    return scopeDesc;
  }

  public void setScopeDesc(String scopeDesc) {
    this.scopeDesc = scopeDesc;
  }

  @PrePersist
  public void prePersist() {
    this.setActive(true);
  }

  public static ProjectStaffing toEntity(ProjectStaffingDto projectStaffingDto) {

    ProjectStaffing projectStaffing = new ProjectStaffing(
            projectStaffingDto.isActive(),
            projectStaffingDto.getBaseId(),
            projectStaffingDto.getCustomerName(),
            projectStaffingDto.getSalesOrder(),
            projectStaffingDto.getStartDate(),
            projectStaffingDto.getEndDate(),
            projectStaffingDto.getEstHours(),
            UserInfo.toEntity(projectStaffingDto.getStaffOwner()),
            Region.toEntity(projectStaffingDto.getRegion()),
            projectStaffingDto.getScopeDesc());

    return projectStaffing;
  }
}
