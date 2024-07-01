package org.imaginnovate.Controller;

import org.imaginnovate.Dto.TimesheetDto;
import org.imaginnovate.Service.TimesheetService;

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

@Path("/Timesheet")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TimesheetController {

    @Inject
    TimesheetService timesheetService;

    @GET
    @Path("/getall")
    public Response all() {
        return timesheetService.getAllTimesheets();
    }

    @POST
    @Path("/create")
    @Transactional
    public Response createTimesheet(TimesheetDto timesheetDto) {
        return timesheetService.createTimesheet(timesheetDto);
    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") Long id) {
        return timesheetService.getTimesheetById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteTimesheet(@PathParam("id") int id) {
        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateTimesheet(@PathParam("id") int id, TimesheetDto timesheetDto) {
        return Response.ok().build();
    }
}