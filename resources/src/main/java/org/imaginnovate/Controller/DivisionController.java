package org.imaginnovate.Controller;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.imaginnovate.Dto.DivisionDto;
import org.imaginnovate.Service.DivisionService;

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

@Path("/divisions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DivisionController {

    @Inject
    DivisionService divisionService;

    @GET
    public Response getAllDivisions() {
        return divisionService.getAllDivisions();
    }

    @POST
    @Transactional
    public Response createDivision(@RequestBody DivisionDto divisionDto) {
        return divisionService.createDivision(divisionDto);
    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") int id) {
        return divisionService.getDivisionById(id);
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateDivision(@PathParam("id") int id, DivisionDto divisionsDto) {
        return divisionService.updateDivision(id, divisionsDto);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteDivision(@PathParam("id") int id) {
        return divisionService.deleteDivision(id);

    }
}
