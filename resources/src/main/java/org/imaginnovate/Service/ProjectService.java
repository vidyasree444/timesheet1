package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.ProjectDto;
import org.imaginnovate.Entity.Division;
import org.imaginnovate.Entity.Project;
import org.imaginnovate.Repository.DivisionRepo;
import org.imaginnovate.Repository.ProjectRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class ProjectService {

    @Inject
    ProjectRepo projectsRepo;

    @Inject
    DivisionRepo divisionsRepo;

    public Response getAllProjects() {
        try {
            List<ProjectDto> projectsDto = projectsRepo.findAllProjects();
            if (projectsDto.isEmpty()) {
                return Response.status(Status.NOT_FOUND).entity("There is no existing projects").build();
            }
            return Response.status(Response.Status.OK).entity(projectsDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response createProject(ProjectDto projectDto) {
        try {
            if (projectDto.getId() != null) {
                Project existingProject = projectsRepo.findById(projectDto.getId());
                if (existingProject != null) {
                    return Response.status(Status.CONFLICT)
                            .entity("Project with ID " + projectDto.getId() + " already exists").build();
                }
            }

            Project projects = new Project();
            projects.setId(projectDto.getId());
            projects.setName(projectDto.getName());
            projects.setDescription(projectDto.getDescription());

            if (projectDto.getDivisionId() != 0) {
                Division divisions = divisionsRepo.findById(projectDto.getDivisionId());
                if (divisions != null) {
                    projects.setDivisionId(divisions);
                } else {
                    return Response.status(Status.NOT_FOUND)
                            .entity("Division with ID " + projectDto.getDivisionId() + " not found").build();
                }
            }
            projects.setCreatedBy(projectDto.getCreatedBy());
            projects.setCreatedOn(projectDto.getCreatedOn());
            projects.setModifiedBy(projectDto.getModifiedBy());
            projects.setModifiedOn(projectDto.getModifiedOn());
            projects.setDeletedBy(projectDto.getDeletedBy());
            projects.setDeletedOn(projectDto.getDeletedOn());

            projectsRepo.persist(projects);

            return Response.status(Response.Status.OK).entity(projectDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response updateProject(int id, ProjectDto projectDto) {
        try {
            Project projects = projectsRepo.findById(id);
            if (projects == null) {
                return Response.status(Status.NOT_FOUND).entity("Project with ID " + id + " not found").build();
            }

            projects.name = projectDto.getName();
            projects.description = projectDto.getDescription();

            if (projectDto.getDivisionId() != 0) {
                Division divisions = divisionsRepo.findById(projectDto.getDivisionId());
                if (divisions != null) {
                    projects.divisionId = divisions;
                } else {
                    return Response.status(Status.NOT_FOUND)
                            .entity("Division with ID " + projectDto.getDivisionId() + " not found").build();
                }
            } else {
                projects.divisionId = null;
            }

            projectsRepo.persist(projects);
            return Response.ok(projects).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    public Response getProjectById(int id) {
        try {
            Project projects = projectsRepo.findById(id);
            if (projects == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Project with ID " + id + " not found")
                        .build();
            }
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(projects.getId());
            projectDto.setName(projects.name);
            projectDto.setDescription(projects.description);
            if (projects.divisionId != null) {
                projectDto.setDivisionId(projects.getDivisionId().getId());
            }
            projectDto.setCreatedBy(projects.getCreatedBy());
            projectDto.setCreatedOn(projects.getCreatedOn());
            projectDto.setDeletedBy(projects.getDeletedBy());
            projectDto.setDeletedOn(projects.getDeletedOn());
            projectDto.setModifiedBy(projects.getModifiedBy());
            projectDto.setModifiedOn(projects.getModifiedOn());
            return Response.status(Response.Status.OK).entity(projectDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteProject(int id) {
        try {
            Project projects = projectsRepo.findById(id);
            if (projects == null) {
                return Response.status(Status.NOT_FOUND).entity("Project with ID " + id + " not found").build();
            } else {
                projectsRepo.delete(projects);
            }
            return Response.status(Response.Status.OK)
                    .entity("ProjectService with ID " + id + " deleted successfully")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}