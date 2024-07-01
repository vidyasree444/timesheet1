package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.UserDto;
import org.imaginnovate.Entity.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class UserRepo implements PanacheRepository<User> {

    @PersistenceContext
    EntityManager em;

    public List<UserDto> findAllUsers() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.UserDto(u.id, u.userName, u.password, e.id, u.resetToken, " +
                "u.resetTokenExpiresAt, u.createdBy, u.createdOn, u.modifiedBy, u.modifiedOn, u.deletedBy, u.deletedOn) "
                +
                "FROM User u LEFT JOIN u.employeeId e";

        TypedQuery<UserDto> query = em.createQuery(jpql, UserDto.class);
        return query.getResultList();
    }

    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    public void deleteById(int id) {
        em.remove(em.find(User.class, id));
    }
}