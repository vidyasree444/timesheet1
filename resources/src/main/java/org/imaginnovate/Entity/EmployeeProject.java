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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "employee_projects", uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "project_id" }))
@JsonPropertyOrder({ "id", "employeeId", "projectId", "canApproveTimesheets", "createdBy", "createdOn", "modifiedBy",
        "modifiedOn", "deletedBy", "deletedOn" })
public class EmployeeProject extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    public Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    public Project projectId;

    @Column(name = "can_approve_timesheets", nullable = false)
    public Boolean canApproveTimesheets;

    public EmployeeProject() {
    }

    public EmployeeProject(Integer id, Employee employeeId, Project projectId, Boolean canApproveTimesheets) {
        this.id = id;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.canApproveTimesheets = canApproveTimesheets;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Project getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public Boolean isCanApproveTimesheets() {
        return this.canApproveTimesheets;
    }

    public Boolean getCanApproveTimesheets() {
        return this.canApproveTimesheets;
    }

    public void setCanApproveTimesheets(Boolean canApproveTimesheets) {
        this.canApproveTimesheets = canApproveTimesheets;
    }

    public EmployeeProject id(Integer id) {
        setId(id);
        return this;
    }

    public EmployeeProject employeeId(Employee employeeId) {
        setEmployeeId(employeeId);
        return this;
    }

    public EmployeeProject projectId(Project projectId) {
        setProjectId(projectId);
        return this;
    }

    public EmployeeProject canApproveTimesheets(Boolean canApproveTimesheets) {
        setCanApproveTimesheets(canApproveTimesheets);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", employeeId='" + getEmployeeId() + "'" +
                ", projectId='" + getProjectId() + "'" +
                ", canApproveTimesheets='" + isCanApproveTimesheets() + "'" +
                "}" + "," + super.toString();
    }

}
