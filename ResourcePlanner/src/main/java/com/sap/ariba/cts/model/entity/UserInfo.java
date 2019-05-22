package com.sap.ariba.cts.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;


/**
 * UserInfo Entity to hold user details 
 *
 */
@Entity
public class UserInfo {

	/**
	 * variable mapped to USER_ID column in table
	 */
	@Id
	@Column(name="USER_ID")
	private String userId;

	/**
	 * variable mapped to FIRST_NAME column in table
	 */
	@Column(name="FIRST_NAME")
	private String firstName;
	
	/**
	 * variable mapped to LAST_NAME column in table
	 */
	@Column(name="LAST_NAME")
	private String lastName;
	
	/**
	 * variable mapped to MIDDLE_NAME column in table
	 */
	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	/**
	 * variable mapped to EMAIL column in table
	 */
	@Column(name="EMAIL")
	private String email;
	
	/**
	 * variable mapped to COUNTRY_CODE column in table
	 */
	@Column(name="COUNTRY_CODE")
	private Country country;
	
	/**
	 * variable mapped to CITY_CODE column in table
	 */
	@Column(name="CITY_CODE")
	private City city;
	
	/**
	 * variable mapped to IS_ACTIVE column in table
	 */
	@Column(name="IS_ACTIVE")
	private boolean active;
	//TODO 1. active flag - to think with active, inactive and purged?
	// maybe have a field like userState that tracks above and active flag maintains user active or inactive
	
	//TODO - Enable after AuditListener have been tested
	/**
	 * variable mapped to CREATED_DATE column in table
	 */
	@Column(name = "CREATED_DATE")
	//@Temporal(TemporalType.TIMESTAMP)
	//@CreatedDate
	private Date createdOn;


	/**
	 * variable mapped to LAST_MODIFIED column in table
	 */
	@Column(name = "LAST_MODIFIED")
	//@Temporal(TemporalType.TIMESTAMP)
	//@LastModifiedDate
	private Date lastModified;

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
