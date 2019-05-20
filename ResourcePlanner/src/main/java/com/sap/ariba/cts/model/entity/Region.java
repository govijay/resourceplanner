package com.sap.ariba.cts.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;

import java.io.Serializable;
import java.util.Collection;

/**
 * Region Entity Object.
 *
 * <p>Top level object in the organizational hierarchy like
 * NA,LAC,EMEA,APAC</p>
 *
 * @author Vijay.G
 * @version $Id : $
 */
@Entity
@Table(name = "REGIONS")
@ClassMetaProperty(code = "REG")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regionSequenceGenerator")
  @GenericGenerator(name = "regionSequenceGenerator",
          strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
          parameters = {
                  @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "region_seq"),
                  @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                  @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
          })
  @Column(name = "BASE_ID")
  private String baseId;

  @Column(name = "REGION_CODE")
  @NotBlank
  private String regionCode;

  @Column(name = "REGION_NAME")
  @NotBlank
  private String regionName;

  @OneToMany(mappedBy = "regionBaseId", fetch = FetchType.LAZY, orphanRemoval = false)
  @JsonManagedReference
  private Collection<Department> departments;

  @OneToMany(mappedBy = "regionBaseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonManagedReference
  private Collection<Country> countries;

  /**
   * Instantiates a new Region.
   */
  protected Region() {
    super();
  }


  /**
   * Gets base id.
   *
   * @return the base id
   */
  public String getBaseId() {
    return baseId;
  }

  /**
   * Sets base id.
   *
   * @param baseId the base id
   */
  public void setBaseId(String baseId) {
    this.baseId = baseId;
  }

  /**
   * Gets region code.
   *
   * @return the region code
   */
  public String getRegionCode() {
    return regionCode;
  }

  /**
   * Sets region code.
   *
   * @param regionCode the region code
   */
  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }

  /**
   * Gets region name.
   *
   * @return the region name
   */
  public String getRegionName() {
    return regionName;
  }

  /**
   * Sets region name.
   *
   * @param regionName the region name
   */
  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  /**
   * Gets departments.
   *
   * @return the departments
   */
  public Collection<Department> getDepartments() {
    return departments;
  }

  /**
   * Sets departments.
   *
   * @param departments the departments
   */
  public void setDepartments(Collection<Department> departments) {
    this.departments = departments;
  }

  /**
   * Gets countries.
   *
   * @return the countries
   */
  public Collection<Country> getCountries() {
    return countries;
  }

  /**
   * Sets countries.
   *
   * @param countries the countries
   */
  public void setCountries(Collection<Country> countries) {
    this.countries = countries;
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
    return "Region{" +
            "baseId='" + baseId + '\'' +
            ", regionCode='" + regionCode + '\'' +
            ", regionName='" + regionName + '\'' +
            '}';
  }
}
