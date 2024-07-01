package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Entity.TimesheetStatus;
import org.imaginnovate.Repository.TimesheetStatusRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class TimesheetStatusService {

    @Inject
    TimesheetStatusRepo timesheetStatusRepo;

    public Response getAllTimesheetStatus() {
        try {
            List<TimesheetStatus> timesheetStatuses = timesheetStatusRepo.listAll();
            if (timesheetStatuses.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("There is no timesheetStatuses ").build();
            }
            return Response.status(Response.Status.OK).entity(timesheetStatuses).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response createTimesheetStatus(TimesheetStatus timesheetStatus) {
        try {
            TimesheetStatus existingTimesheetStatus = timesheetStatusRepo.findById(timesheetStatus.getId());
            if (existingTimesheetStatus != null) {
                return Response.status(Response.Status.CONFLICT).entity("The timesheetStatus already existed").build();
            }
            timesheetStatus.setId(timesheetStatus.getId());
            timesheetStatus.setName(timesheetStatus.getName());
            timesheetStatus.setCreatedOn(timesheetStatus.getCreatedOn());
            timesheetStatusRepo.persist(timesheetStatus);
            return Response.status(Response.Status.OK).entity(timesheetStatus).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    public Response getTimesheetStatusById(Byte id) {
        try {
            TimesheetStatus timesheetStatus = timesheetStatusRepo.findById(id);
            if (timesheetStatus == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("There is no timesheetStatus found with Id " + "getId()").build();
            }
            return Response.status(Response.Status.OK).entity(timesheetStatus).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response updateTimesheetStatus(TimesheetStatus timesheetStatus) {
        try {
            TimesheetStatus existingTimesheetStatus = timesheetStatusRepo.findById(timesheetStatus.getId());
            if (existingTimesheetStatus == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No timesheets found with the Id" + "getId()")
                        .build();
            }
            existingTimesheetStatus.setId(timesheetStatus.getId());
            existingTimesheetStatus.setName(timesheetStatus.getName());
            timesheetStatusRepo.persist(existingTimesheetStatus);
            return Response.status(Response.Status.OK).entity(existingTimesheetStatus).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteTimesheetStatus(Byte id) {
        try {
            TimesheetStatus timesheetStatus = timesheetStatusRepo.findById(id);
            if (timesheetStatus == null) {
                throw new RuntimeException("Timesheet status not found");
            } else {
                timesheetStatusRepo.delete(timesheetStatus);
            }
            return Response.status(Response.Status.OK).entity(timesheetStatus).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}