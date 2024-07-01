package org.imaginnovate.Service;

import java.util.List;
import java.util.Optional;

import org.imaginnovate.Dto.EmployeeDto;
import org.imaginnovate.Entity.Division;
import org.imaginnovate.Entity.Employee;
import org.imaginnovate.Repository.DivisionRepo;
import org.imaginnovate.Repository.EmployeeRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class EmployeeService {
    @Inject
    EmployeeRepo employeeRepo;

    @Inject
    DivisionRepo divisionRepo;

    public Response getAllEmployees() {
        try {
            List<EmployeeDto> employees = employeeRepo.findAllEmployees();
            if (employees.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("There are no employees")
                        .build();
            }
            return Response.ok(employees).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error while fetching divisions")
                    .build();
        }
    }

    @Transactional
    public Response addEmployee(EmployeeDto employeeDto) {
        if (employeeRepo.findById(employeeDto.getId()) != null) {
            return Response.status(Response.Status.CONFLICT).entity("Employee already exists").build();
        }
        Employee employee = convertToEntity(employeeDto);
        employeeRepo.persist(employee);
        return Response.status(Response.Status.CREATED).entity(convertToDto(employee)).build();
    }

    @Transactional
    public Response updateEmployee(int id, EmployeeDto employeeDto) {
        try {
            Employee employee = findEmployeeByIdOptional(id);
            if (employee == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Employee with ID " + id + " not found")
                        .build();
            }
            updateEmployeeDetails(employee, employeeDto);
            return Response.ok().entity(convertToDto(employee)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @Transactional
    public Response deleteEmployee(int id) {
        try {
            Employee employee = findEmployeeByIdOptional(id);
            if (employee == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Employee with ID " + id + " not found")
                        .build();
            } else {
                employeeRepo.delete(employee);
            }
            return Response.status(Response.Status.OK).entity(convertToDto(employee)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    public Response getEmployeeById(int id) {
        try {
            Employee employee = employeeRepo.findById(id);
            if (employee == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee  with the id is not found")
                        .build();
            }
            return Response.ok(convertToDto(employee)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error while fetching division by ID")
                    .build();
        }
    }

    private Employee convertToEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        updateEmployeeDetails(employee, dto);
        return employee;
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setGender(employee.getGender());
        dto.setEmail(employee.getEmail());
        dto.setDesignation(employee.getDesignation());
        dto.setStartDate(employee.getStartDate());
        dto.setEndDate(employee.getEndDate());
        dto.setDivisionId(Optional.ofNullable(employee.getDivisionId()).map(Division::getId).orElse(null));
        dto.setReportsToId(Optional.ofNullable(employee.getReportsToId()).map(Employee::getId).orElse(null));
        dto.setCreatedBy(employee.getCreatedBy());
        dto.setCreatedOn(employee.getCreatedOn());
        dto.setModifiedBy(employee.getModifiedBy());
        dto.setModifiedOn(employee.getModifiedOn());
        dto.setDeletedBy(employee.getDeletedBy());
        dto.setDeletedOn(employee.getDeletedOn());
        return dto;
    }

    private void updateEmployeeDetails(Employee employee, EmployeeDto dto) {
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setGender(dto.getGender());
        employee.setDesignation(dto.getDesignation());
        employee.setStartDate(dto.getStartDate());
        employee.setEndDate(dto.getEndDate());
        employee.setDivisionId(findDivisionById(dto.getDivisionId()));
        employee.setReportsToId(findEmployeeByIdOptional(dto.getReportsToId()));
        employee.setCreatedBy(dto.getCreatedBy());
        employee.setCreatedOn(dto.getCreatedOn());
        employee.setModifiedBy(dto.getModifiedBy());
        employee.setModifiedOn(dto.getModifiedOn());
        employee.setDeletedBy(dto.getDeletedBy());
        employee.setDeletedOn(dto.getDeletedOn());
    }

    private Division findDivisionById(Integer integer) {
        try {
            if (integer == null)
                return null;
            return Optional.ofNullable(divisionRepo.findById(integer))
                    .orElseThrow(() -> new IllegalArgumentException("Division with ID " + integer + " not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error finding division by ID", e);
        }
    }

    public Employee findEmployeeByIdOptional(Integer integer) {
        if (integer == null || integer == 0)
            return null;
        return employeeRepo.findByIdOptional(integer)
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + integer + " not found"));
    }

}
