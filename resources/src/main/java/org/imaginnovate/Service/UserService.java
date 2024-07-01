package org.imaginnovate.Service;

import java.util.List;

import org.imaginnovate.Dto.UserDto;
import org.imaginnovate.Entity.Employee;
import org.imaginnovate.Entity.User;
import org.imaginnovate.Repository.EmployeeRepo;
import org.imaginnovate.Repository.UserRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepo usersRepo;

    @Inject
    EmployeeRepo employeesRepo;

    public Response getAllUsers() {
        try {
            List<UserDto> users = usersRepo.findAllUsers();
            if (users.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("There are no existing users").build();
            }
            return Response.status(Response.Status.OK).entity(users).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response addUser(UserDto usersDto) {
        if (usersDto.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User ID is required").build();
        }

        if (usersRepo.findById(usersDto.getId()) != null) {
            return Response.status(Response.Status.CONFLICT).entity("User already exists").build();
        }

        User user = new User();
        user.setId(usersDto.getId());
        user.userName = usersDto.getUserName();
        user.password = usersDto.getPassword();
        user.resetToken = usersDto.getResetToken();
        user.resetTokenExpiresAt = usersDto.getResetTokenExpiresAt();

        if (usersDto.getEmployeeId() != 0) {
            Employee employee = employeesRepo.findById(usersDto.getEmployeeId());
            if (employee != null) {
                user.employeeId = employee;
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee with ID " + usersDto.getEmployeeId() + " not found").build();
            }
        }

        user.setCreatedOn(usersDto.getCreatedOn());
        usersRepo.persist(user);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @SuppressWarnings("null")
    public Response getUserById(int id) {
        try {
            User users = usersRepo.findById(id);
            if (users != null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No users found").build();
            }
            UserDto dto = new UserDto();
            dto.setId(users.getId());
            dto.setUserName(users.userName);
            dto.setPassword(users.password);
            dto.setEmployeeId(users.employeeId.getId());
            dto.setResetToken(users.resetToken);
            dto.setResetTokenExpiresAt(users.resetTokenExpiresAt);
            dto.setCreatedBy(users.getCreatedBy());
            dto.setCreatedOn(users.getCreatedOn());
            if (users.getDeletedBy() != null) {
                dto.setDeletedBy(users.getDeletedBy());
            }
            dto.setDeletedOn(users.getDeletedOn());
            if (users.getModifiedBy() != null) {
                dto.setModifiedBy(users.getModifiedBy());
            }
            dto.setModifiedOn(users.getModifiedOn());
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(" Internal Server Error").build();
        }
    }

    @Transactional
    public Response updateUser(UserDto usersDto) {
        try {
            User users = usersRepo.findById(usersDto.getId());
            if (users == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No users founded").build();
            }
            users.userName = usersDto.getUserName();
            users.password = usersDto.getPassword();
            users.resetToken = usersDto.getResetToken();
            users.resetTokenExpiresAt = usersDto.getResetTokenExpiresAt();
            if (usersDto.getEmployeeId() != 0) {
                Employee employee = employeesRepo.findById(usersDto.getEmployeeId());
                if (employee != null) {
                    users.employeeId = employee;
                } else {
                    return Response.status(Response.Status.OK).entity(employee).build();
                }
            }
            usersRepo.persist(users);
            return Response.status(Response.Status.OK).entity(users).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }

    @Transactional
    public Response deleteUser(int id) {
        try {
            User users = usersRepo.findById(id);
            if (users == null) {
                throw new IllegalArgumentException("User with ID " + id + " not found");
            } else {
                usersRepo.deleteById(id);
            }
            return Response.status(Response.Status.OK).entity("User with ID " + id + " deleted successfully").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server error").build();
        }
    }
}
