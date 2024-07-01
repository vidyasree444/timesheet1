package org.imaginnovate.Controller;

import org.imaginnovate.Dto.UserDto;
import org.imaginnovate.Service.UserService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserController {
    @Inject
    UserService usersService;

    @GET
    @Path("/getall")
    public Response getAllUsers() {
        return usersService.getAllUsers();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response addUser(UserDto usersDto) {
        return usersService.addUser(usersDto);
    }

    @GET
    @Path("/get/{id}")
    public Response getUserById(@PathParam("id") int id) {
        return usersService.getUserById(id);

    }

    @GET
    @Path("/getbyusername/{username}")
    public Response getUserByUserId(@PathParam("id") Integer id) {
        return usersService.getUserById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") int id) {
        return usersService.deleteUser(id);
    }
}
