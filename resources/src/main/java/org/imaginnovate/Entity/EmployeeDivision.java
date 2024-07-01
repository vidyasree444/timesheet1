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
@Table(name = "employee_divisions", uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id",
        "division_id" }))
@JsonPropertyOrder({ "id", "employeeId", "divisionId", "canApproveTimesheets", "createdBy", "createdOn", "modifiedBy",
        "modifiedOn", "deletedBy", "deletedOn" })
public class EmployeeDivision extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    public Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    public Division divisionId;

    @Column(name = "can_approve_timesheets", nullable = false)
    public Boolean canApproveTimesheets;

    public EmployeeDivision() {
    }

    public EmployeeDivision(Integer id, Employee employeeId, Division divisionId, Boolean canApproveTimesheets) {
        this.id = id;
        this.employeeId = employeeId;
        this.divisionId = divisionId;
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

    public Division getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(Division divisionId) {
        this.divisionId = divisionId;
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

    public EmployeeDivision id(Integer id) {
        setId(id);
        return this;
    }

    public EmployeeDivision employeeId(Employee employeeId) {
        setEmployeeId(employeeId);
        return this;
    }

    public EmployeeDivision divisionId(Division divisionId) {
        setDivisionId(divisionId);
        return this;
    }

    public EmployeeDivision canApproveTimesheets(Boolean canApproveTimesheets) {
        setCanApproveTimesheets(canApproveTimesheets);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", employeeId='" + getEmployeeId() + "'" +
                ", divisionId='" + getDivisionId() + "'" +
                ", canApproveTimesheets='" + isCanApproveTimesheets() + "'" +
                "}" + "," + super.toString();
    }

}