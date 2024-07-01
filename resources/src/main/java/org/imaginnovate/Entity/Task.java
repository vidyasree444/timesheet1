package org.imaginnovate.Entity;

import java.io.Serializable;

import org.imaginnovate.BaseClass.BaseAuditFields;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
@JsonPropertyOrder({ "id", "name", "description", "createdBy", "createdOn", "modifiedBy", "modifiedOn",
        "deletedBy", "deletedOn" })
public class Task extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 40)
    private String name;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    public Task() {
    }

    public Task(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task id(Integer id) {
        setId(id);
        return this;
    }

    public Task name(String name) {
        setName(name);
        return this;
    }

    public Task description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                "}" + "," + super.toString();
    }

}
