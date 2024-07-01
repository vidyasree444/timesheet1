package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.TimesheetDto;
import org.imaginnovate.Entity.Employee;
import org.imaginnovate.Entity.EmployeeProject;
import org.imaginnovate.Entity.ProjectTask;
import org.imaginnovate.Entity.Timesheet;
import org.imaginnovate.Entity.TimesheetStatus;
import org.imaginnovate.Repository.EmployeeProjectRepo;
import org.imaginnovate.Repository.EmployeeRepo;
import org.imaginnovate.Repository.ProjectTaskRepo;
import org.imaginnovate.Repository.TimesheetRepo;
import org.imaginnovate.Repository.TimesheetStatusRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class TimesheetService {
    @Inject
    TimesheetRepo timesheetRepo;

    @Inject
    EmployeeRepo employeesRepo;

    @Inject
    TimesheetStatusRepo timesheetStatusRepo;

    @Inject
    EmployeeProjectRepo employeeProjectRepo;

    @Inject
    ProjectTaskRepo projectTaskRepo;

    public Response getAllTimesheets() {

        List<TimesheetDto> timesheets = timesheetRepo.findAllTimesheets();
        if (timesheets == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("There are no timesheets").build();
        }
        return Response.status(Response.Status.OK).entity(timesheets).build();

    }

    @Transactional
    public Response createTimesheet(TimesheetDto timesheetDto) {
        try {
            Timesheet timesheet = new Timesheet();
            if (timesheetDto.getEmployeeProjectId() != null) {
                EmployeeProject employeeProject = employeeProjectRepo.findById(timesheetDto.getEmployeeProjectId());
                if (employeeProject != null) {
                    timesheet.setEmployeeProjectId(employeeProject);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Employee Project ID").build();
                }
            }
            if (timesheetDto.getProjectTaskId() != null) {
                ProjectTask projectTask = projectTaskRepo.findById(timesheetDto.getProjectTaskId());
                if (projectTask != null) {
                    timesheet.setProjectTaskId(projectTask);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Project Task ID").build();
                }
            }

            timesheet.setDescription(timesheetDto.getDescription());
            timesheet.setHoursWorked(timesheetDto.getHoursWorked());
            timesheet.setSubmittedOn(timesheetDto.getSubmittedOn());

            if (timesheetDto.getSubmittedBy() != null) {
                Employee submittedBy = employeesRepo.findById(timesheetDto.getSubmittedBy());
                if (submittedBy != null) {
                    timesheet.setSubmittedBy(submittedBy);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Submitted By ID").build();
                }
            }

            // Set Status if valid ID provided
            if (timesheetDto.getStatus() != null) {
                TimesheetStatus status = timesheetStatusRepo.findById(timesheetDto.getStatus());
                if (status != null) {
                    timesheet.setStatus(status);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Status ID").build();
                }
            }

            // Set ApprovedBy if valid ID provided
            if (timesheetDto.getApprovedBy() != null) {
                Employee approvedBy = employeesRepo.findById(timesheetDto.getApprovedBy());
                if (approvedBy != null) {
                    timesheet.setApprovedBy(approvedBy);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Approved By ID").build();
                }
            }

            timesheet.setCreatedBy(timesheetDto.getCreatedBy());
            timesheet.setCreatedOn(timesheetDto.getCreatedOn());
            timesheet.setModifiedBy(timesheetDto.getModifiedBy());
            timesheet.setModifiedOn(timesheetDto.getModifiedOn());
            timesheet.setDeletedBy(timesheetDto.getDeletedBy());
            timesheet.setDeletedOn(timesheetDto.getDeletedOn());

            timesheetRepo.persist(timesheet);

            return Response.status(Response.Status.CREATED).entity(timesheetDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response updateTimesheet(TimesheetDto timesheetDto) {
        try {
            Timesheet existingTimesheet = timesheetRepo.findById(timesheetDto.getId());
            if (existingTimesheet == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Timesheet not found").build();
            }

            if (timesheetDto.getEmployeeProjectId() != null) {
                EmployeeProject employeeProject = employeeProjectRepo.findById(timesheetDto.getEmployeeProjectId());
                if (employeeProject != null) {
                    existingTimesheet.setEmployeeProjectId(employeeProject);
                } else {
                    throw new IllegalArgumentException("Invalid Employee Project ID");
                }
            }

            if (timesheetDto.getProjectTaskId() != null) {
                ProjectTask projectTask = projectTaskRepo.findById(timesheetDto.getProjectTaskId());
                if (projectTask != null) {
                    existingTimesheet.setProjectTaskId(projectTask);
                } else {
                    throw new IllegalArgumentException("Invalid Project Task ID");
                }
            }

            existingTimesheet.setDescription(timesheetDto.getDescription());
            existingTimesheet.setHoursWorked(timesheetDto.getHoursWorked());

            if (timesheetDto.getSubmittedBy() != null) {
                Employee submittedBy = employeesRepo.findById(timesheetDto.getSubmittedBy());
                if (submittedBy != null) {
                    existingTimesheet.setSubmittedBy(submittedBy);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Submitted By ID").build();
                }
            }

            existingTimesheet.setSubmittedOn(timesheetDto.getSubmittedOn());

            if (timesheetDto.getStatus() != null) {
                TimesheetStatus status = timesheetStatusRepo.findById(timesheetDto.getStatus());
                if (status != null) {
                    existingTimesheet.setStatus(status);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Status ID").build();
                }
            }

            if (timesheetDto.getApprovedBy() != null) {
                Employee approvedBy = employeesRepo.findById(timesheetDto.getApprovedBy());
                if (approvedBy != null) {
                    existingTimesheet.setApprovedBy(approvedBy);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Approved By ID").build();
                }
            }

            existingTimesheet.setCreatedBy(timesheetDto.getCreatedBy());
            existingTimesheet.setCreatedOn(timesheetDto.getCreatedOn());
            existingTimesheet.setDeletedBy(timesheetDto.getDeletedBy());
            existingTimesheet.setDeletedOn(timesheetDto.getDeletedOn());
            existingTimesheet.setModifiedBy(timesheetDto.getModifiedBy());
            existingTimesheet.setModifiedOn(timesheetDto.getModifiedOn());
            timesheetRepo.persist(existingTimesheet);
            return Response.status(Response.Status.OK).entity(existingTimesheet).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteTimesheet(@PathParam("id") int id) {
        try {
            Timesheet timesheet = timesheetRepo.findById(id);
            if (timesheet == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Timesheet with id " + id + " not found")
                        .build();
            } else {
                timesheetRepo.delete(timesheet);
            }

            return Response.status(Response.Status.OK)
                    .entity("Timesheet with id " + id + " is deleted")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    public Response getTimesheetById(Long id) {
        try {
            Timesheet timesheet = timesheetRepo.findById(id);
            if (timesheet == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Timesheet not found").build();
            }
            TimesheetDto timesheetDto = new TimesheetDto();
            timesheetDto.setId(timesheet.getId());
            timesheetDto.setEmployeeProjectId(timesheet.getEmployeeProjectId().getId());
            timesheetDto.setProjectTaskId(timesheet.getProjectTaskId().getId());
            timesheetDto.setDescription(timesheet.getDescription());
            timesheetDto.setHoursWorked(timesheet.getHoursWorked());
            timesheetDto.setSubmittedBy(timesheet.getSubmittedBy().getId());
            timesheetDto.setSubmittedOn(timesheet.getSubmittedOn());
            timesheetDto.setStatus(timesheet.getStatus().getId());
            timesheetDto.setApprovedBy(timesheet.getApprovedBy().getId());
            timesheetDto.setCreatedBy(timesheet.getCreatedBy());
            timesheetDto.setCreatedOn(timesheet.getCreatedOn());
            timesheetDto.setDeletedBy(timesheet.getDeletedBy());
            timesheetDto.setDeletedOn(timesheet.getDeletedOn());
            timesheetDto.setModifiedBy(timesheet.getModifiedBy());
            timesheetDto.setModifiedOn(timesheet.getModifiedOn());
            return Response.ok(timesheetDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}