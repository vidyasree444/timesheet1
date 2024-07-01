package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.ProjectTaskDto;
import org.imaginnovate.Entity.ProjectTask;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class ProjectTaskRepo implements PanacheRepository<ProjectTask> {

    @PersistenceContext
    EntityManager em;

    public List<ProjectTaskDto> findAllProjectTasks() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.ProjectTaskDto(pt.id, pt.projectId.id, pt.taskId.id, pt.createdBy, pt.createdOn, pt.modifiedBy, pt.modifiedOn, pt.deletedBy, pt.deletedOn) "
                +
                "FROM ProjectTask pt LEFT JOIN pt.projectId";

        TypedQuery<ProjectTaskDto> query = em.createQuery(jpql, ProjectTaskDto.class);
        return query.getResultList();
    }

    public ProjectTask findById(Integer projectTaskId) {
        return projectTaskId == null ? null : em.find(ProjectTask.class, projectTaskId);
    }

    public ProjectTask findByProjectAndTask(Integer projectId, Integer taskId) {
        String jpql = "SELECT pt FROM ProjectTask pt WHERE pt.projectId.id = :projectId AND pt.taskId.id = :taskId";
        TypedQuery<ProjectTask> query = em.createQuery(jpql, ProjectTask.class);
        query.setParameter("projectId", projectId);
        query.setParameter("taskId", taskId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
