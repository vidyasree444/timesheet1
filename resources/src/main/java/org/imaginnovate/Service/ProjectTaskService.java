package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.ProjectTaskDto;
import org.imaginnovate.Entity.Project;
import org.imaginnovate.Entity.ProjectTask;
import org.imaginnovate.Entity.Task;
import org.imaginnovate.Repository.ProjectRepo;
import org.imaginnovate.Repository.ProjectTaskRepo;
import org.imaginnovate.Repository.TaskRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ProjectTaskService {

    @Inject
    ProjectTaskRepo projectTasksRepo;

    @Inject
    ProjectRepo projectsRepo;

    @Inject
    TaskRepo tasksRepo;

    public Response getAllProjectTasks() {
        try {
            List<ProjectTaskDto> projectTasks = projectTasksRepo.findAllProjectTasks();
            if (projectTasks.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("There are no project tasks").build();
            }
            return Response.status(Response.Status.OK).entity(projectTasks).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response createProjectTask(ProjectTaskDto projectTasksDto) {
        ProjectTask projectTask = projectTasksRepo.findByProjectAndTask(projectTasksDto.getProjectId(),
                projectTasksDto.getTaskId());
        if (projectTask != null) {
            return Response.status(Response.Status.CONFLICT).entity("Project Task already exists").build();
        }

        Project project = projectsRepo.findById(projectTasksDto.getProjectId());
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Project with ID " + projectTasksDto.getProjectId() + " not found").build();
        }

        Task task = tasksRepo.findById(projectTasksDto.getTaskId());
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Task with ID " + projectTasksDto.getTaskId() + " not found").build();
        }

        ProjectTask newProjectTask = new ProjectTask();
        newProjectTask.setProjectId(project);
        newProjectTask.setTaskId(task);
        newProjectTask.setCreatedOn(projectTasksDto.getCreatedOn());
        projectTasksRepo.persist(newProjectTask);

        return Response.status(Response.Status.CREATED).entity(projectTasksDto).build();
    }

    @Transactional
    public Response updateProjectTask(Long id, ProjectTaskDto projectTasksDto) {
        try {
            ProjectTask projectTasks = projectTasksRepo.findById(id);
            if (projectTasks == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Project Task with ID " + id + " not found")
                        .build();
            }

            Project projects = projectsRepo.findById(projectTasksDto.getProjectId());
            if (projects == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Project with ID " + projectTasksDto.getProjectId() + " not found").build();
            }

            Task tasks = tasksRepo.findById(projectTasksDto.getTaskId());
            if (tasks == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Task with ID " + projectTasksDto.getTaskId() + " not found").build();
            }

            projectTasks.projectId = projects;
            projectTasks.taskId = tasks;

            projectTasksRepo.persist(projectTasks);
            return Response.status(Response.Status.OK).entity(projectTasks).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    public Response getProjectTaskById(Long id) {
        try {
            ProjectTask projectTasks = projectTasksRepo.findById(id);
            if (projectTasks == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Project Task with ID " + id + " not found")
                        .build();
            }
            ProjectTaskDto dto = new ProjectTaskDto();
            dto.setId(projectTasks.getId());
            dto.setProjectId(projectTasks.projectId.getId());
            dto.setTaskId(projectTasks.taskId.getId());
            dto.setCreatedBy(projectTasks.getCreatedBy());
            dto.setCreatedOn(projectTasks.getCreatedOn());
            if (projectTasks.getDeletedBy() != null) {
                dto.setDeletedBy(projectTasks.getDeletedBy());
            }
            dto.setDeletedOn(projectTasks.getDeletedOn());
            if (projectTasks.getModifiedBy() != null) {
                dto.setModifiedBy(projectTasks.getModifiedBy());
            }
            dto.setModifiedOn(projectTasks.getModifiedOn());

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteProjectTask(Long id) {
        try {
            ProjectTask projectTasks = projectTasksRepo.findById(id);
            if (projectTasks == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Project Task with ID " + id + " not found")
                        .build();
            } else {
                projectTasksRepo.delete(projectTasks);
            }
            return Response.status(Response.Status.OK)
                    .entity("Project Task with ID " + id + " deleted successfully")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}