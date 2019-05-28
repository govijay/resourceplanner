package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.JobRoleDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

import java.io.Serializable;

@Entity
@Table(name = "JOBROLES")
@ClassMetaProperty(code = "JR")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobRole extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobRoleSequenceGenerator")
  @GenericGenerator(name = "jobRoleSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "jobrole_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "JOBROLE_CODE")
  private String jobRoleCode;

  @Column(name = "JOBROLE_NAME")
  private String jobRoleName;

  /**
   * Instantiates a new Base entity.
   */
  public JobRole() {
    super();
  }

  /**
   * Instantiates a new Base entity.
   *
   * @param active the active
   */
  public JobRole(boolean active, String baseId, String jobRoleCode, String jobRoleName) {
    super(active);
    this.baseId = baseId;
    this.jobRoleCode = jobRoleCode;
    this.jobRoleName = jobRoleName;
  }

  public String getBaseId() {
    return baseId;
  }

  public void setBaseId(String baseId) {
    this.baseId = baseId;
  }

  public String getJobRoleCode() {
    return jobRoleCode;
  }

  public void setJobRoleCode(String jobRoleCode) {
    this.jobRoleCode = jobRoleCode;
  }

  public String getJobRoleName() {
    return jobRoleName;
  }

  public void setJobRoleName(String jobRoleName) {
    this.jobRoleName = jobRoleName;
  }

  @PrePersist
  public void prePersist() {
    this.setActive(true);
  }

  public static JobRole toEntity(JobRoleDto jobRoleDto) {
    return new JobRole(
            jobRoleDto.isActive(),
            jobRoleDto.getBaseId(),
            jobRoleDto.getJobRoleCode(),
            jobRoleDto.getJobRoleName()
    );

  }

  @Override
  public String toString() {
    return "JobRole{" +
            "baseId='" + baseId + '\'' +
            ", jobRoleCode='" + jobRoleCode + '\'' +
            ", jobRoleName='" + jobRoleName + '\'' +
            '}';
  }
}
