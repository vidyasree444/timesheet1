package org.imaginnovate.Repository;

import java.util.List;

import org.imaginnovate.Dto.ProjectDto;
import org.imaginnovate.Entity.Division;
import org.imaginnovate.Entity.Project;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ProjectRepo implements PanacheRepository<Project> {

    @PersistenceContext
    EntityManager em;

    public List<ProjectDto> findAllProjects() {
        String jpql = "SELECT NEW org.imaginnovate.Dto.ProjectDto(p.id, p.name, p.description, d.id, " +
                "p.createdBy, p.createdOn, p.modifiedBy, p.modifiedOn, p.deletedBy, p.deletedOn) " +
                "FROM Project p LEFT JOIN p.divisionId d";
        return em.createQuery(jpql, ProjectDto.class).getResultList();
    }

    public List<Project> findByDivisionId(Division divisions) {
        String jpql = "SELECT p FROM Project p WHERE p.divisionId = :division";
        return em.createQuery(jpql, Project.class).setParameter("division", divisions).getResultList();
    }

    public Project findById(Integer id) {
        return em.find(Project.class, id);
    }

}