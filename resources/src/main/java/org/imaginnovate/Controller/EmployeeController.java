package org.imaginnovate.Controller;

import org.imaginnovate.Dto.EmployeeDto;
import org.imaginnovate.Service.EmployeeService;

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

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    EmployeeService employeeService;

    @GET
    @Path("/getall")
    public Response all() {
        return employeeService.getAllEmployees();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response addEmployee(EmployeeDto employeeDto) {
        return employeeService.addEmployee(employeeDto);
    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateEmployee(@PathParam("id") int id, EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id, employeeDto);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteEmployee(@PathParam("id") int id) {
        return employeeService.deleteEmployee(id);

    }
}
