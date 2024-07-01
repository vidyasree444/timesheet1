package org.imaginnovate.Dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "employeeId", "divisionId", "canApproveTimesheets", "createdBy", "createdOn", "modifiedBy",
        "modifiedOn", "deletedBy", "deletedOn" })
public class EmployeeDivisionDto extends BaseAuditFieldsDTO {

    private Integer id;
    private Integer employeeId;
    private Integer divisionId;
    private Boolean canApproveTimesheets;

    public EmployeeDivisionDto() {
    }

    public EmployeeDivisionDto(Integer id, Integer employeeId, Integer divisionId, Boolean canApproveTimesheets,
            Integer createdBy, LocalDateTime createdOn,
            Integer modifiedBy, LocalDateTime modifiedOn, Integer deletedBy, LocalDateTime deletedOn) {
        super(createdBy, createdOn, modifiedBy, modifiedOn, deletedBy, deletedOn);
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

    public Integer getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Boolean getCanApproveTimesheets() {
        return this.canApproveTimesheets;
    }

    public void setCanApproveTimesheets(Boolean canApproveTimesheets) {
        this.canApproveTimesheets = canApproveTimesheets;
    }

    public EmployeeDivisionDto id(Integer id) {
        setId(id);
        return this;
    }

    public EmployeeDivisionDto employeeId(Integer employeeId) {
        setEmployeeId(employeeId);
        return this;
    }

    public EmployeeDivisionDto divisionId(Integer divisionId) {
        setDivisionId(divisionId);
        return this;
    }

    public EmployeeDivisionDto canApproveTimesheets(Boolean canApproveTimesheets) {
        setCanApproveTimesheets(canApproveTimesheets);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", employeeId='" + getEmployeeId() + "'" +
                ", divisionId='" + getDivisionId() + "'" +
                ", canApproveTimesheets='" + getCanApproveTimesheets() + "'" +
                "}" + "," + super.toString();
    }

}
