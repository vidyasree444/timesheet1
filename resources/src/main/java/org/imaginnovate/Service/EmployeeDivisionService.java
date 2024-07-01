package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.EmployeeDivisionDto;
import org.imaginnovate.Entity.Division;
import org.imaginnovate.Entity.Employee;
import org.imaginnovate.Entity.EmployeeDivision;
import org.imaginnovate.Repository.DivisionRepo;
import org.imaginnovate.Repository.EmployeeDivisionRepo;
import org.imaginnovate.Repository.EmployeeRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class EmployeeDivisionService {

    @Inject
    EmployeeDivisionRepo employeeDivisionsRepo;

    @Inject
    DivisionRepo divisionsRepo;

    @Inject
    EmployeeRepo employeesRepo;

    @Transactional
    public Response getAllEmployeeDivisions() {
        try {
            List<EmployeeDivisionDto> employeeDivisionsDto = employeeDivisionsRepo.findAllEmployeeDivisions();
            if (employeeDivisionsDto.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("There are no employee divisions").build();
            }
            return Response.status(Response.Status.OK).entity(employeeDivisionsDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
        }
    }

    @Transactional
    public Response createEmployeeDivision(EmployeeDivisionDto employeeDivisionsDto) {
        try {
            Division division = divisionsRepo.findById(employeeDivisionsDto.getDivisionId());
            if (division == null) {
                return Response.status(Response.Status.CONFLICT).entity("Division not found").build();
            }

            Employee employee = employeesRepo.findById(employeeDivisionsDto.getEmployeeId());
            if (employee == null) {
                return Response.status(Response.Status.CONFLICT).entity("Employee not found").build();
            }

            boolean exists = employeeDivisionsRepo.existsByDivisionIdAndEmployeeId(employeeDivisionsDto.getDivisionId(),
                    employeeDivisionsDto.getEmployeeId());
            if (exists) {
                return Response.status(Response.Status.CONFLICT).entity("Employee Division already exists").build();
            }

            EmployeeDivision employeeDivision = new EmployeeDivision();
            employeeDivision.setEmployeeId(employee);
            employeeDivision.setDivisionId(division);
            employeeDivision.setCanApproveTimesheets(employeeDivisionsDto.getCanApproveTimesheets());
            employeeDivision.setCreatedOn(employeeDivisionsDto.getCreatedOn());
            employeeDivisionsRepo.persist(employeeDivision);

            return Response.status(Response.Status.CREATED).entity(employeeDivision).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    public Response getEmployeeDivisionById(int id) {
        try {
            EmployeeDivision ed = employeeDivisionsRepo.findById(id);
            if (ed == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee Division with ID " + id + " not found")
                        .build();
            }

            EmployeeDivisionDto employeeDivisionDto = new EmployeeDivisionDto(ed.getId(), ed.getEmployeeId().getId(),
                    ed.getDivisionId().getId(),
                    ed.getCanApproveTimesheets(), ed.getCreatedBy(), ed.getCreatedOn(),
                    ed.getDeletedBy(), ed.getDeletedOn(), ed.getModifiedBy(), ed.getModifiedOn());

            return Response.status(Response.Status.OK)
                    .entity(employeeDivisionDto)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response updateEmployeeDivision(int id, EmployeeDivisionDto employeeDivisionsDto) {
        try {
            EmployeeDivision employeeDivisions = employeeDivisionsRepo.findById(id);
            if (employeeDivisions == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No employee division found").build();
            }

            if (employeeDivisionsDto.getEmployeeId() != 0) {
                Employee employees = employeesRepo.findById(employeeDivisionsDto.getEmployeeId());
                if (employees != null) {
                    employeeDivisions.employeeId = employees;
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Employee with ID " + employeeDivisionsDto.getEmployeeId() + " not found").build();
                }
            }

            if (employeeDivisionsDto.getDivisionId() != 0) {
                Division divisions = divisionsRepo.findById(employeeDivisionsDto.getDivisionId());
                if (divisions != null) {
                    employeeDivisions.divisionId = divisions;
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Division with ID " + employeeDivisionsDto.getDivisionId() + " not found").build();
                }
            }

            employeeDivisions.canApproveTimesheets = employeeDivisionsDto.getCanApproveTimesheets();
            employeeDivisions.setModifiedBy(employeeDivisionsDto.getModifiedBy());
            employeeDivisions.setModifiedOn(employeeDivisionsDto.getModifiedOn());
            employeeDivisionsRepo.persist(employeeDivisions);
            return Response.ok(employeeDivisions).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteEmployeeDivision(int id) {
        try {
            EmployeeDivision ed = employeeDivisionsRepo.findByemployeeDivisionId(id);
            if (ed == null) {
                throw new IllegalArgumentException("EmployeeDivision with ID " + id + " not found");
            } else {
                employeeDivisionsRepo.delete(ed);
            }
            return Response.status(Response.Status.OK)
                    .entity("EmployeeDivision with ID " + id + " deleted successfully")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}
