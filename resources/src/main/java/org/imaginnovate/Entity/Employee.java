package org.imaginnovate.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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
@Table(name = "employees")
@JsonPropertyOrder({ "id", "firstName", "lastName", "gender", "email", "designation", "startDate", "endDate",
        "divisionId", "reportsToId", "createdBy", "createdOn", "modifiedBy", "modifiedOn", "deletedBy", "deletedOn" })
public class Employee extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 60)
    public String firstName;

    @Column(name = "last_name", nullable = false, length = 40)
    public String lastName;

    @Column(name = "gender", nullable = false, length = 1)
    public char gender;

    @Column(name = "email", unique = true, nullable = false, length = 80)
    public String email;

    @Column(name = "designation", nullable = false, length = 30)
    public String designation;

    @Column(name = "start_date", nullable = false)
    public LocalDateTime startDate;

    @Column(name = "end_date")
    public LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    public Division divisionId;

    @ManyToOne
    @JoinColumn(name = "reports_to_id", nullable = true)
    public Employee reportsToId;

    public Employee() {
    }

    public Employee(Integer id, String firstName, String lastName, char gender, String email, String designation,
            LocalDateTime startDate, LocalDateTime endDate, Division divisionId, Employee reportsToId) {
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

    public void setGender(char gender) {
        this.gender = gender;
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

    public Division getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(Division divisionId) {
        this.divisionId = divisionId;
    }

    public Employee getReportsToId() {
        return this.reportsToId;
    }

    public void setReportsToId(Employee reportsToId) {
        this.reportsToId = reportsToId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName) && Objects.equals(gender, employee.gender)
                && Objects.equals(email, employee.email) && Objects.equals(designation, employee.designation)
                && Objects.equals(startDate, employee.startDate) && Objects.equals(endDate, employee.endDate)
                && Objects.equals(divisionId, employee.divisionId) && Objects.equals(reportsToId, employee.reportsToId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, email, designation, startDate, endDate, divisionId,
                reportsToId);
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