package org.imaginnovate.Controller;

import org.imaginnovate.Dto.EmployeeDivisionDto;
import org.imaginnovate.Service.EmployeeDivisionService;

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

@Path("/employeeDivisions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeDivisionController {

    @Inject
    EmployeeDivisionService employeeDivisionService;

    @GET
    @Path("/getall")
    public Response getAll() {
        Response employeeDivisionsDtos = employeeDivisionService.getAllEmployeeDivisions();
        return Response.ok(employeeDivisionsDtos).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response createProjectTask(EmployeeDivisionDto employeeDivisionsDto) {
        Response employeeDivisions = employeeDivisionService.createEmployeeDivision(employeeDivisionsDto);
        return Response.status(Response.Status.CREATED).entity(employeeDivisions).build();
    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") int id) {
        Response dto = employeeDivisionService.getEmployeeDivisionById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("EmployeeDivision not found for ID: " + id)
                    .build();
        }
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteEmployeeDivision(@PathParam("id") int id) {
        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateEmployeeDivision(@PathParam("id") int id, EmployeeDivisionDto employeeDivisionsDto) {
        return Response.ok().build();
    }

}
