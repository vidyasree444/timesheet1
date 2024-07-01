package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.DivisionDto;
import org.imaginnovate.Entity.Division;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class DivisionRepo implements PanacheRepository<Division> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DivisionDto> findAllDivisions() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.DivisionDto(" +
                "d.id, d.name, p.id, d.createdBy, d.createdOn, d.modifiedBy, d.modifiedOn, d.deletedBy, d.deletedOn) " +
                "FROM Division d LEFT JOIN d.parentId p";
        TypedQuery<DivisionDto> query = entityManager.createQuery(jpql, DivisionDto.class);
        return query.getResultList();
    }

    public List<Division> findByDivisionId(int divisionId) {
        return find("division.id", divisionId).list();
    }

    public Division findById(int id) {
        try {
            String hql = "FROM Division d WHERE d.id = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", id);
            return (Division) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Division findByName(String name) {
        try {
            String hql = "FROM Division d WHERE d.name = :name";
            Query query = entityManager.createQuery(hql);
            query.setParameter("name", name);
            return (Division) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}