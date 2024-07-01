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
@Table(name = "users")
@JsonPropertyOrder({ "id", "userName", "employeeId", "password", "resetToken", "resetTokenExpiresAt", "createdBy",
        "createdOn", "modifiedBy", "modifiedOn", "deletedBy", "deletedOn" })
public class User extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 60)
    public String userName;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    public Employee employeeId;

    @Column(name = "password", nullable = false, length = 12)
    public String password;

    @Column(name = "reset_token", nullable = false, length = 10)
    public String resetToken;

    @Column(name = "reset_token_expires_at", nullable = false)
    public LocalDateTime resetTokenExpiresAt;

    public User() {
    }

    public User(Integer id, String userName, Employee employeeId, String password, String resetToken,
            LocalDateTime resetTokenExpiresAt) {
        this.id = id;
        this.userName = userName;
        this.employeeId = employeeId;
        this.password = password;
        this.resetToken = resetToken;
        this.resetTokenExpiresAt = resetTokenExpiresAt;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Employee getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetToken() {
        return this.resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiresAt() {
        return this.resetTokenExpiresAt;
    }

    public void setResetTokenExpiresAt(LocalDateTime resetTokenExpiresAt) {
        this.resetTokenExpiresAt = resetTokenExpiresAt;
    }

    public User id(Integer id) {
        setId(id);
        return this;
    }

    public User userName(String userName) {
        setUserName(userName);
        return this;
    }

    public User employeeId(Employee employeeId) {
        setEmployeeId(employeeId);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User resetToken(String resetToken) {
        setResetToken(resetToken);
        return this;
    }

    public User resetTokenExpiresAt(LocalDateTime resetTokenExpiresAt) {
        setResetTokenExpiresAt(resetTokenExpiresAt);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", userName='" + getUserName() + "'" +
                ", employeeId='" + getEmployeeId() + "'" +
                ", password='" + getPassword() + "'" +
                ", resetToken='" + getResetToken() + "'" +
                ", resetTokenExpiresAt='" + getResetTokenExpiresAt() + "'" +
                "}" + "," + super.toString();
    }

}