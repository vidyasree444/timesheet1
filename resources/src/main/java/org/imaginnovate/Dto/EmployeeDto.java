package org.imaginnovate.Dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "firstName", "lastName", "gender", "email", "designation", "startDate", "endDate",
        "divisionId", "reportsToId", "createdBy", "createdOn", "modifiedBy", "modifiedOn", "deletedBy", "deletedOn" })
public class EmployeeDto extends BaseAuditFieldsDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private char gender;
    private String email;
    private String designation;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer divisionId;
    private Integer reportsToId;

    public EmployeeDto() {
    }

    public EmployeeDto(Integer id, String firstName, String lastName, char gender, String email, String designation,
            LocalDateTime startDate, LocalDateTime endDate, Integer divisionId, Integer reportsToId, Integer createdBy,
            LocalDateTime createdOn, Integer modifiedBy, LocalDateTime modifiedOn, Integer deletedBy,
            LocalDateTime deletedOn) {
        super(createdBy, createdOn, modifiedBy, modifiedOn, deletedBy, deletedOn);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.designation = designation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.divisionId = divisionId;
        this.reportsToId = reportsToId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char c) {
        this.gender = c;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Integer getReportsToId() {
        return this.reportsToId;
    }

    public void setReportsToId(Integer reportsToId) {
        this.reportsToId = reportsToId;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", gender='" + getGender() + "'" +
                ", email='" + getEmail() + "'" +
                ", designation='" + getDesignation() + "'" +
                ", startDate='" + getStartDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", divisionId='" + getDivisionId() + "'" +
                ", reportsToId='" + getReportsToId() + "'" +
                "}" + "," + super.toString();
    }

}
