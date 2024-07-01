package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.EmployeeProjectDto;
import org.imaginnovate.Entity.EmployeeProject;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmployeeProjectRepo implements PanacheRepository<EmployeeProject> {

    @PersistenceContext
    EntityManager em; // Removed static

    @Transactional
    public List<EmployeeProjectDto> findAllEmployeeProjects() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.EmployeeProjectDto(" +
                "ep.id, " +
                "ep.employeeId.id, " +
                "ep.projectId.id, " +
                "ep.canApproveTimesheets, " +
                "ep.createdBy, " +
                "ep.createdOn, " +
                "ep.modifiedBy, " +
                "ep.modifiedOn, " +
                "ep.deletedBy, " +
                "ep.deletedOn) " +
                "FROM EmployeeProject ep " +
                "LEFT JOIN ep.employeeId emp " +
                "LEFT JOIN ep.projectId proj";

        TypedQuery<EmployeeProjectDto> query = em.createQuery(jpql, EmployeeProjectDto.class);
        return query.getResultList();
    }

    public EmployeeProject findById(int id) {
        return id == 0 ? null : em.find(EmployeeProject.class, id);
    }

    public void delete(EmployeeProject employeeProjects) {
        em.remove(employeeProjects);
    }

    public void persist(EmployeeProject employeeProjects) {
        em.persist(employeeProjects);
    }

    public EmployeeProject findByEmployeeAndProject(Integer employeeId, Integer projectId) {
        String jpql = "SELECT ep FROM EmployeeProject ep " +
                "WHERE ep.employeeId.id = :employeeId AND ep.projectId.id = :projectId";
        TypedQuery<EmployeeProject> query = em.createQuery(jpql, EmployeeProject.class);
        query.setParameter("employeeId", employeeId);
        query.setParameter("projectId", projectId);
        List<EmployeeProject> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
