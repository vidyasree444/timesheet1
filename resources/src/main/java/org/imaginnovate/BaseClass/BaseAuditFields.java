package org.imaginnovate.BaseClass;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseAuditFields {

    @Column(name = "created_by")
    public Integer createdBy;

    @Column(name = "created_on", nullable = false)
    public LocalDateTime createdOn;

    @Column(name = "modified_by")
    public Integer modifiedBy;

    @Column(name = "modified_on")
    public LocalDateTime modifiedOn;

    @Column(name = "deleted_by")
    public Integer deletedBy;

    @Column(name = "deleted_on")
    public LocalDateTime deletedOn;

    public BaseAuditFields() {
    }

    public BaseAuditFields(Integer createdBy, LocalDateTime createdOn, Integer modifiedBy, LocalDateTime modifiedOn,
            Integer deletedBy, LocalDateTime deletedOn) {
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
        this.deletedBy = deletedBy;
        this.deletedOn = deletedOn;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Integer getDeletedBy() {
        return this.deletedBy;
    }

    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getDeletedOn() {
        return this.deletedOn;
    }

    public void setDeletedOn(LocalDateTime deletedOn) {
        this.deletedOn = deletedOn;
    }

    public BaseAuditFields createdBy(Integer createdBy) {
        setCreatedBy(createdBy);
        return this;
    }

    public BaseAuditFields createdOn(LocalDateTime createdOn) {
        setCreatedOn(createdOn);
        return this;
    }

    public BaseAuditFields modifiedBy(Integer modifiedBy) {
        setModifiedBy(modifiedBy);
        return this;
    }

    public BaseAuditFields modifiedOn(LocalDateTime modifiedOn) {
        setModifiedOn(modifiedOn);
        return this;
    }

    public BaseAuditFields deletedBy(Integer deletedBy) {
        setDeletedBy(deletedBy);
        return this;
    }

    public BaseAuditFields deletedOn(LocalDateTime deletedOn) {
        setDeletedOn(deletedOn);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " createdBy='" + getCreatedBy() + "'" +
                ", createdOn='" + getCreatedOn() + "'" +
                ", modifiedBy='" + getModifiedBy() + "'" +
                ", modifiedOn='" + getModifiedOn() + "'" +
                ", deletedBy='" + getDeletedBy() + "'" +
                ", deletedOn='" + getDeletedOn() + "'" +
                "}";
    }

}
