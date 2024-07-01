package org.imaginnovate.Entity;

import java.io.Serializable;

import org.imaginnovate.BaseClass.BaseAuditFields;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "divisions")
@JsonPropertyOrder({ "id", "name", "parentId", "createdBy", "createdOn", "modifiedBy", "modifiedOn", "deletedBy",
        "deletedOn" })
public class Division extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 40)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Division parentId;

    public Division() {
    }

    public Division(Integer id, String name, Division parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Division getParentId() {
        return this.parentId;
    }

    public void setParentId(Division parentId) {
        this.parentId = parentId;
    }

    public Division id(Integer id) {
        setId(id);
        return this;
    }

    public Division name(String name) {
        setName(name);
        return this;
    }

    public Division parentId(Division parentId) {
        setParentId(parentId);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", parentId='" + getParentId() + "'" +
                "}" + "," + super.toString();
    }

}