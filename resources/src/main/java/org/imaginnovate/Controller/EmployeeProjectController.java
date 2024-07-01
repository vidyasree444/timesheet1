package org.imaginnovate.Controller;

import org.imaginnovate.Dto.EmployeeProjectDto;
import org.imaginnovate.Service.EmployeeProjectService;

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

@Path("/employeeprojects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeProjectController {
    @Inject
    EmployeeProjectService employeeProjectsService;

    @GET
    @Path("/getall")
    public Response all() {
        return employeeProjectsService.getAllEmployeeProjects();
    }

    @POST
    @Transactional
    public Response createEmployeeProjects(EmployeeProjectDto employeeProjectsDto) {
        return employeeProjectsService.createEmployeeProjects(employeeProjectsDto);
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateEmployeeProjects(@PathParam("id") int id, EmployeeProjectDto employeeProjectsDto) {
        return employeeProjectsService.updateEmployeeProjects(id, employeeProjectsDto);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteEmployeeProjects(@PathParam("id") int id) {
        return employeeProjectsService.deleteEmployeeProjects(id);
    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") int id) {
        return employeeProjectsService.getEmployeeProjectById(id);
    }
}
