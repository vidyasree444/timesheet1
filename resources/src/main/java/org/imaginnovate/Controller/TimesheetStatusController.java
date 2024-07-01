package org.imaginnovate.Controller;

import org.imaginnovate.Entity.TimesheetStatus;
import org.imaginnovate.Service.TimesheetStatusService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/timesheetstatus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimesheetStatusController {
    @Inject
    TimesheetStatusService timesheetStatusService;

    @GET
    @Path("/getall")
    @Transactional
    public Response all() {
        return timesheetStatusService.getAllTimesheetStatus();
    }

    @POST
    @Path("/create")
    @Transactional
    public Response create(TimesheetStatus timesheetStatus) {
        return timesheetStatusService.createTimesheetStatus(timesheetStatus);
    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") Byte id) {
        return timesheetStatusService.getTimesheetStatusById(id);
    }

    @POST
    @Path("/update")
    @Transactional
    public Response update(TimesheetStatus timesheetStatus) {
        return timesheetStatusService.updateTimesheetStatus(timesheetStatus);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Byte id) {
        return timesheetStatusService.deleteTimesheetStatus(id);
    }
}