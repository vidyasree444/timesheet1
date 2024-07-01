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
@Table(name = "projects")
@JsonPropertyOrder({ "id", "name", "description", "divisionId", "createdBy", "createdOn", "modifiedBy", "modifiedOn",
        "deletedBy", "deletedOn" })
public class Project extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    public String name;

    @Column(name = "description", length = 200)
    public String description;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    public Division divisionId;

    public Project() {
    }

    public Project(Integer id, String name, String description, Division divisionId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.divisionId = divisionId;
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

    public Division getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(Division divisionId) {
        this.divisionId = divisionId;
    }

    public Project id(Integer id) {
        setId(id);
        return this;
    }

    public Project name(String name) {
        setName(name);
        return this;
    }

    public Project description(String description) {
        setDescription(description);
        return this;
    }

    public Project divisionId(Division divisionId) {
        setDivisionId(divisionId);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", divisionId='" + getDivisionId() + "'" +
                "}" + "," + super.toString();
    }

}
