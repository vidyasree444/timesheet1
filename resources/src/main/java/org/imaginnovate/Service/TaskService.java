package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.TaskDto;
import org.imaginnovate.Entity.Task;
import org.imaginnovate.Repository.TaskRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepo tasksRepo;

    public Response getAllTasks() {
        try {
            List<TaskDto> tasks = tasksRepo.findAllTasks();
            if (tasks.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("There are no tasks").build();
            }
            return Response.status(Response.Status.OK).entity(tasks).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response createTask(TaskDto taskDto) {
        try {
            if (taskDto.getId() != null) {
                Task existingTask = tasksRepo.findById(taskDto.getId());
                if (existingTask != null) {
                    return Response.status(Response.Status.CONFLICT)
                            .entity("Task with name '" + taskDto.getName() + "' already exists")
                            .build();
                }
            }

            Task task = new Task();
            task.setId(taskDto.getId());
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setCreatedBy(taskDto.getCreatedBy());
            task.setCreatedOn(taskDto.getCreatedOn());
            task.setDeletedBy(taskDto.getDeletedBy());
            task.setDeletedOn(taskDto.getDeletedOn());
            task.setModifiedBy(taskDto.getModifiedBy());
            task.setModifiedOn(taskDto.getModifiedOn());

            tasksRepo.persist(task);

            return Response.status(Response.Status.CREATED)
                    .entity(task)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response updateTask(Long id, TaskDto taskDto) {
        try {
            Task task = tasksRepo.findById(id);
            if (task == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Task with ID " + id + " not found")
                        .build();
            }

            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setModifiedBy(taskDto.getModifiedBy());
            task.setModifiedOn(taskDto.getModifiedOn());

            tasksRepo.persist(task);

            return Response.status(Response.Status.OK)
                    .entity("Task with ID " + id + " updated successfully")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    public Response getTaskById(Long id) {
        try {
            Task task = tasksRepo.findById(id);
            if (task == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Task with ID " + id + " not found").build();
            }

            TaskDto dto = new TaskDto();
            dto.setId(task.getId());
            dto.setName(task.getName());
            dto.setDescription(task.getDescription());
            dto.setCreatedBy(task.getCreatedBy());
            dto.setCreatedOn(task.getCreatedOn());
            dto.setDeletedBy(task.getDeletedBy());
            dto.setDeletedOn(task.getDeletedOn());
            dto.setModifiedBy(task.getModifiedBy());
            dto.setModifiedOn(task.getModifiedOn());

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteTask(Long id) {
        try {
            Task task = tasksRepo.findById(id);
            if (task == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Task with ID " + id + " not found")
                        .build();
            } else {
                tasksRepo.delete(task);
            }
            return Response.status(Response.Status.OK)
                    .entity("Task with ID " + id + " deleted successfully")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}