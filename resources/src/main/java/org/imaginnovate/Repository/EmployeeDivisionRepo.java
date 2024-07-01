package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.EmployeeDivisionDto;
import org.imaginnovate.Entity.EmployeeDivision;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class EmployeeDivisionRepo implements PanacheRepository<EmployeeDivision> {
    @PersistenceContext
    EntityManager em;

    public List<EmployeeDivisionDto> findAllEmployeeDivisions() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.EmployeeDivisionDto(" +
                "ed.id, ed.employeeId.id, ed.divisionId.id, ed.canApproveTimesheets, " +
                "ed.createdBy, ed.createdOn, ed.modifiedBy, ed.modifiedOn, ed.deletedBy, ed.deletedOn) " +
                "FROM EmployeeDivision ed " +
                "LEFT JOIN ed.divisionId d " +
                "ORDER BY ed.employeeId.id ASC, ed.divisionId.id ASC";

        TypedQuery<EmployeeDivisionDto> query = em.createQuery(jpql, EmployeeDivisionDto.class);

        return query.getResultList();
    }

    public EmployeeDivision findByemployeeDivisionId(Integer id) {
        return em.find(EmployeeDivision.class, id);
    }

    public EmployeeDivision findById(int id) {
        return em.find(EmployeeDivision.class, id);
    }

    public void persist(EmployeeDivision employeeDivision) {
        em.persist(employeeDivision);
    }

    public boolean existsByDivisionIdAndEmployeeId(Integer divisionId, Integer employeeId) {
        return false;
    }
}