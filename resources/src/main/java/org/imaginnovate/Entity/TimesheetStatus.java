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
@Table(name = "timesheet_status")
@JsonPropertyOrder({ "id", "name", "createdBy", "createdOn", "modifiedBy", "modifiedOn", "deletedBy", "deletedOn" })
public class TimesheetStatus extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    public TimesheetStatus() {
    }

    public TimesheetStatus(Byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public Byte getId() {
        return this.id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimesheetStatus id(Byte id) {
        setId(id);
        return this;
    }

    public TimesheetStatus name(String name) {
        setName(name);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                "}" + "," + super.toString();
    }

}
