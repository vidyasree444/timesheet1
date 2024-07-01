package org.imaginnovate.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "timesheets")
@JsonPropertyOrder({ "id", "employeeProjectId", "projectTaskId", "description", "hoursWorked", "submittedBy",
        "submittedOn", "status", "approvedBy", "createdBy", "createdOn", "modifiedBy", "modifiedOn", "deletedBy",
        "deletedOn" })
public class Timesheet extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_project_id", nullable = false)
    private EmployeeProject employeeProjectId;

    @ManyToOne
    @JoinColumn(name = "project_task_id", nullable = false)
    private ProjectTask projectTaskId;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "hours_worked", nullable = false)
    private Short hoursWorked;

    @ManyToOne
    @JoinColumn(name = "submitted_by", nullable = false)
    private Employee submittedBy;

    @Column(name = "submitted_on", nullable = false)
    private LocalDateTime submittedOn;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private TimesheetStatus status;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = false)
    private Employee approvedBy;

    public Timesheet() {
    }

    public Timesheet(Integer id, EmployeeProject employeeProjectId, ProjectTask projectTaskId, String description,
            Short hoursWorked, Employee submittedBy, LocalDateTime submittedOn, TimesheetStatus status,
            Employee approvedBy) {
        this.id = id;
        this.employeeProjectId = employeeProjectId;
        this.projectTaskId = projectTaskId;
        this.description = description;
        this.hoursWorked = hoursWorked;
        this.submittedBy = submittedBy;
        this.submittedOn = submittedOn;
        this.status = status;
        this.approvedBy = approvedBy;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeProject getEmployeeProjectId() {
        return this.employeeProjectId;
    }

    public void setEmployeeProjectId(EmployeeProject employeeProjectId) {
        this.employeeProjectId = employeeProjectId;
    }

    public ProjectTask getProjectTaskId() {
        return this.projectTaskId;
    }

    public void setProjectTaskId(ProjectTask projectTaskId) {
        this.projectTaskId = projectTaskId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getHoursWorked() {
        return this.hoursWorked;
    }

    public void setHoursWorked(Short hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Employee getSubmittedBy() {
        return this.submittedBy;
    }

    public void setSubmittedBy(Employee submittedBy) {
        this.submittedBy = submittedBy;
    }

    public LocalDateTime getSubmittedOn() {
        return this.submittedOn;
    }

    public void setSubmittedOn(LocalDateTime submittedOn) {
        this.submittedOn = submittedOn;
    }

    public TimesheetStatus getStatus() {
        return this.status;
    }

    public void setStatus(TimesheetStatus status) {
        this.status = status;
    }

    public Employee getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", employeeProjectId='" + getEmployeeProjectId() + "'" +
                ", projectTaskId='" + getProjectTaskId() + "'" +
                ", description='" + getDescription() + "'" +
                ", hoursWorked='" + getHoursWorked() + "'" +
                ", submittedBy='" + getSubmittedBy() + "'" +
                ", submittedOn='" + getSubmittedOn() + "'" +
                ", status='" + getStatus() + "'" +
                ", approvedBy='" + getApprovedBy() + "'" +
                "}" + "," + super.toString();
    }

}