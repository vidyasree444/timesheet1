package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.EmployeeProjectDto;
import org.imaginnovate.Entity.Employee;
import org.imaginnovate.Entity.EmployeeProject;
import org.imaginnovate.Entity.Project;
import org.imaginnovate.Repository.EmployeeProjectRepo;
import org.imaginnovate.Repository.EmployeeRepo;
import org.imaginnovate.Repository.ProjectRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class EmployeeProjectService {

    @Inject
    EmployeeProjectRepo employeeProjectsRepo;

    @Inject
    EmployeeRepo employeesRepo;

    @Inject
    ProjectRepo projectRepo;

    public Response getAllEmployeeProjects() {
        try {
            List<EmployeeProjectDto> employeeProjectsDto = employeeProjectsRepo.findAllEmployeeProjects();
            if (employeeProjectsDto.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("There are no employee projects").build();
            }
            return Response.status(Response.Status.OK).entity(employeeProjectsDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response createEmployeeProjects(EmployeeProjectDto employeeProjectsDto) {
        try {
            EmployeeProject existingEmployeeProject = employeeProjectsRepo.findByEmployeeAndProject(
                    employeeProjectsDto.getEmployeeId(), employeeProjectsDto.getProjectId());
            if (existingEmployeeProject != null) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("Employee-Project pair already exists")
                        .build();
            }

            Employee employees = employeesRepo.findById(employeeProjectsDto.getEmployeeId());
            if (employees == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee with ID " + employeeProjectsDto.getEmployeeId() + " not found")
                        .build();
            }

            Project projects = projectRepo.findById(employeeProjectsDto.getProjectId());
            if (projects == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Project with ID " + employeeProjectsDto.getProjectId() + " not found")
                        .build();
            }

            EmployeeProject employeeProjects = new EmployeeProject();
            employeeProjects.setId(employeeProjectsDto.getId());
            employeeProjects.employeeId = employees;
            employeeProjects.projectId = projects;
            employeeProjects.canApproveTimesheets = employeeProjectsDto.getCanApproveTimesheets();
            employeeProjects.setCreatedOn(employeeProjectsDto.getCreatedOn());

            employeeProjectsRepo.persist(employeeProjects);

            return Response.status(Response.Status.CREATED).entity(employeeProjectsDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response updateEmployeeProjects(int id, EmployeeProjectDto employeeProjectsDto) {
        try {
            EmployeeProject employeeProjects = employeeProjectsRepo.findById(id);
            if (employeeProjects == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("There is no employee project with ID " + id + "")
                        .build();
            }

            Employee employees = employeesRepo.findById(employeeProjectsDto.getEmployeeId());
            if (employees == null) {
                throw new IllegalArgumentException(
                        "Employee with ID " + employeeProjectsDto.getEmployeeId() + " not found");
            }

            Project projects = projectRepo.findById(employeeProjectsDto.getProjectId());
            if (projects == null) {
                throw new IllegalArgumentException(
                        "Project with ID " + employeeProjectsDto.getProjectId() + " not found");
            }

            employeeProjects.employeeId = employees;
            employeeProjects.projectId = projects;
            employeeProjects.canApproveTimesheets = employeeProjectsDto.getCanApproveTimesheets();

            employeeProjectsRepo.persist(employeeProjects);
            return Response.status(Response.Status.OK).entity(employeeProjectsDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    public Response getEmployeeProjectById(int id) {
        try {
            EmployeeProject ep = employeeProjectsRepo.findById(id);
            if (ep == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("There is no employee project with ID " + id + "")
                        .build();
            }
            EmployeeProjectDto dto = new EmployeeProjectDto();
            dto.setId(ep.getId());
            dto.setEmployeeId(ep.employeeId.getId());
            dto.setProjectId(ep.projectId.getId());
            dto.setCanApproveTimesheets(ep.canApproveTimesheets);
            dto.setCreatedBy(ep.getCreatedBy());
            dto.setCreatedOn(ep.getCreatedOn());
            if (ep.getDeletedBy() != null) {
                dto.setDeletedBy(ep.getDeletedBy());
            }
            dto.setDeletedOn(ep.getDeletedOn());
            if (ep.getModifiedBy() != null) {
                dto.setModifiedBy(ep.getModifiedBy());
            }
            dto.setModifiedOn(ep.getModifiedOn());

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteEmployeeProjects(int id) {
        try {
            EmployeeProject employeeProjects = employeeProjectsRepo.findById(id);
            if (employeeProjects == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee Project with ID " + id + " not found")
                        .build();
            } else {
                employeeProjectsRepo.delete(employeeProjects);
            }
            return Response.status(Response.Status.OK)
                    .entity("Employee Project with ID " + id + " deleted successfully")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}
