package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.TimesheetDto;
import org.imaginnovate.Entity.Timesheet;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class TimesheetRepo implements PanacheRepository<Timesheet> {

    @PersistenceContext
    EntityManager em;

    public List<TimesheetDto> findAllTimesheets() {
        String jpql = "SELECT new org.imaginnovate.Dto.TimesheetDto("
                + "t.id, "
                + "ep.employeeId.id, "
                + "pt.taskId.id, "
                + "t.description, "
                + "t.hoursWorked, "
                + "t.submittedBy.id, "
                + "t.submittedOn, "
                + "t.status.id, "
                + "t.approvedBy.id, "
                + "t.createdBy, "
                + "t.createdOn, "
                + "t.modifiedBy, "
                + "t.modifiedOn, "
                + "t.deletedBy, "
                + "t.deletedOn) "
                + "FROM Timesheet t "
                + "JOIN t.employeeProjectId ep "
                + "JOIN t.projectTaskId pt ";
        TypedQuery<TimesheetDto> query = em.createQuery(jpql, TimesheetDto.class);
        return query.getResultList();

    }

    public boolean deleteTimesheet(int id) {
        return false;

    }

    public Timesheet findById(Integer id) {
        return id == null ? null : em.find(Timesheet.class, id);
    }
}