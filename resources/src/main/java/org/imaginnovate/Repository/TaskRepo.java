package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.TaskDto;
import org.imaginnovate.Entity.Task;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class TaskRepo implements PanacheRepository<Task> {

    @PersistenceContext
    EntityManager em;

    public List<TaskDto> findAllTasks() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.TaskDto(t.id,t.name,t.description,t.createdBy,t.createdOn,t.modifiedBy,t.modifiedOn,t.deletedBy,t.deletedOn) FROM Task t";
        TypedQuery<TaskDto> query = em.createQuery(jpql, TaskDto.class);
        return query.getResultList();

    }

    public Task findById(Integer id) {
        return em.find(Task.class, id);
    }

}