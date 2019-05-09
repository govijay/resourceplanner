package com.sap.ariba.cts.model;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sap.ariba.cts.model.support.ClassMetaProperty;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Base Object.
 * Abstract Class for common Inclusion by other Objects and Providing common root for all objects.
 *
 * @author Vijay.G
 * @version $Id : $
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements BaseEntityInterface, Cloneable, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "BASE_ID")
  private String baseId;

  @Version
  private Integer version;

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


  /**
   * Gets base id.
   *
   * @return the base id
   */
  public String getBaseId() {
    return this.baseId;
  }

  /**
   * Sets base id.
   *
   * @param rootId the root id
   */
  public void setBaseId(String rootId) {
    this.baseId = rootId;
  }

  /**
   * Gets version.
   *
   * @return the version
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * Gets created on.
   *
   * @return the created on
   */
  public Date getCreatedOn() {
    return createdOn;
  }

  /**
   * Sets created on.
   *
   * @param createdOn the created on
   */
  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  /**
   * Gets last modified.
   *
   * @return the last modified
   */
  public Date getLastModified() {
    return lastModified;
  }

  /**
   * Sets last modified.
   *
   * @param lastModified the last modified
   */
  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  /**
   * Is active boolean.
   *
   * @return the boolean
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets active.
   *
   * @param active the active
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Obtain the String name of the JDBC table for the Object.
   *
   * @return String Name of the Table annotated on the Entity Object.
   */
  @Transient
  @Override
  public String getTableName() {
    Table tableAnnotation = this.getClass().getAnnotation(Table.class);
    return tableAnnotation == null ? null : tableAnnotation.name();
  }

  /**
   * Obtain the class code for the Object.
   *
   * @return String class code of the object annotated on the Entity Object using ClassMetaProperty
   */
  public String getClassCode() {
    return (String) getClassMetaProperty("code");
  }

  /**
   * Obtain the Annotation of the class and invokes it.
   *
   * @param property annotation property value.
   * @return Object that's the annotated property method return.
   */

  private Object getClassMetaProperty(String property) {
    Annotation annotation = this.getClass().getAnnotation(ClassMetaProperty.class);
    if (annotation == null) {
      return "";
    }

    try {
      Method m = ClassMetaProperty.class.getMethod(property);
      return m.invoke(annotation, (Object[]) null);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      return null;
    }
  }
}
