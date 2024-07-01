package org.imaginnovate.Controller;

import org.imaginnovate.Dto.ProjectTaskDto;
import org.imaginnovate.Service.ProjectTaskService;

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

@Path("/projectTask")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectTaskController {

    @Inject
    ProjectTaskService projectTasksService;

    @GET
    @Path("/getall")
    public Response all() {
        return projectTasksService.getAllProjectTasks();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response createProjectTask(ProjectTaskDto projectTasksDto) {
        return projectTasksService.createProjectTask(projectTasksDto);

    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") Long id) {
        return projectTasksService.getProjectTaskById(id);

    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteProjectTask(@PathParam("id") int id) {
        return Response.ok(id).build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateProjectTask(@PathParam("id") int id, ProjectTaskDto projectTasksDto) {
        return Response.ok(id).build();
    }

}