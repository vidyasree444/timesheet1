package org.imaginnovate.Entity;

import java.io.Serializable;

import org.imaginnovate.BaseClass.BaseAuditFields;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "project_tasks", uniqueConstraints = @UniqueConstraint(columnNames = { "project_id", "task_id" }))
@JsonPropertyOrder({ "id", "projectId", "taskId", "createdBy", "createdOn", "modifiedBy", "modifiedOn", "deletedBy",
        "deletedOn" })
public class ProjectTask extends BaseAuditFields implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    public Project projectId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    public Task taskId;

    public ProjectTask() {
    }

    public ProjectTask(Integer id, Project projectId, Task taskId) {
        this.id = id;
        this.projectId = projectId;
        this.taskId = taskId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public Task getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

    public ProjectTask id(Integer id) {
        setId(id);
        return this;
    }

    public ProjectTask projectId(Project projectId) {
        setProjectId(projectId);
        return this;
    }

    public ProjectTask taskId(Task taskId) {
        setTaskId(taskId);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", projectId='" + getProjectId() + "'" +
                ", taskId='" + getTaskId() + "'" +
                "}" + "," + super.toString();
    }

}
