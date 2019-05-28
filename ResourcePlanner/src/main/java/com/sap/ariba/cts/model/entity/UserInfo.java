package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


/**
 * UserInfo Entity to hold user details
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserInfo {

  @Version
  private Integer version;

  @Id
  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "MIDDLE_NAME")
  private String middleName;

  @Column(name = "EMAIL")
  private String email;

  @OneToOne
  private Country country;

  @OneToOne
  private City city;

  @Column(name = "CREATED_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdOn;

  @Column(name = "LAST_MODIFIED")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date lastModified;

  @Column(name = "ACTIVE")
  private boolean active;

  public UserInfo() {
  }

  public UserInfo(String userId, String firstName, String lastName, String middleName, String email, boolean active) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.email = email;
    this.active = active;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }


}
