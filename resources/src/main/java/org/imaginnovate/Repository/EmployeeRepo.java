package org.imaginnovate.Repository;

import java.util.List;
import java.util.Optional;

import org.imaginnovate.Dto.EmployeeDto;
import org.imaginnovate.Entity.Employee;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class EmployeeRepo implements PanacheRepository<Employee> {

    @PersistenceContext
    EntityManager em;

    public List<EmployeeDto> findAllEmployees() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.EmployeeDto(" +
                "e.id, e.firstName, e.lastName, e.gender, e.email, e.designation, e.startDate, e.endDate, " +
                "d.id, r.id, e.createdBy, e.createdOn, e.modifiedBy, e.modifiedOn, " +
                "e.deletedBy, e.deletedOn) " +
                "FROM Employee e " +
                "LEFT JOIN e.divisionId d " +
                "LEFT JOIN e.reportsToId r";

        TypedQuery<EmployeeDto> query = em.createQuery(jpql, EmployeeDto.class);

        return query.getResultList();
    }

    public void deleteById(Employee employees) {
        String jpql = "DELETE FROM Employees e WHERE e.id = :id";
        em.createQuery(jpql).setParameter("id", employees.getId()).executeUpdate();
    }

    public Employee findById(Integer id) {
        return find("id", id).firstResult();
    }

    public Optional<Employee> findByIdOptional(Integer reportsToId) {
        return Optional.ofNullable(findById(reportsToId));
    }

}
