package org.imaginnovate.Controller;

import org.imaginnovate.Dto.TaskDto;
import org.imaginnovate.Service.TaskService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {
    @Inject
    TaskService taskService;

    @GET
    @Path("/getall")
    public Response all() {
        return taskService.getAllTasks();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response createTask(TaskDto taskDto) {
        return taskService.createTask(taskDto);

    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") Long id) {
        return taskService.getTaskById(id);

    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteTask(@PathParam("id") Long id) {
        return taskService.deleteTask(id);
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateTask(@PathParam("id") Long id, TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

}