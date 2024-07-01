package org.imaginnovate.Controller;

import org.imaginnovate.Dto.ProjectDto;
import org.imaginnovate.Service.ProjectService;

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

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectController {
    @Inject
    ProjectService projectsService;

    @GET
    @Path("/getall")
    public Response all() {
        return projectsService.getAllProjects();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response createProject(ProjectDto projectDto) {
        return projectsService.createProject(projectDto);
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateProject(@PathParam("id") int id, ProjectDto projectDto) {
        return projectsService.updateProject(id, projectDto);
    }

    @GET
    @Path("/get/{id}")
    public Response getProjectById(@PathParam("id") int id) {
        return projectsService.getProjectById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteProject(@PathParam("id") int id) {
        return projectsService.deleteProject(id);

    }

}