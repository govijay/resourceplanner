package com.sap.ariba.cts.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

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
@JsonIgnoreProperties(
        value = {"createdOn", "lastModified"},
        allowGetters = true
)
public abstract class BaseEntity implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

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
     * Instantiates a new Base entity.
     */
    public BaseEntity() {
    }

    /**
     * Instantiates a new Base entity.
     *
     * @param active the active
     */
    public BaseEntity(boolean active) {
        this.active = active;
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

    /**
     * On pre persist.
     */
    @PrePersist
    public void onPrePersist() {
        audit("INSERT");
    }

    private void audit(String operation) {
        setActive(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return active == that.active &&
                Objects.equals(version, that.version) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(lastModified, that.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, createdOn, lastModified, active);
    }
}
