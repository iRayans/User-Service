package rayan.userservice.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rayan.userservice.core.excpetion.EntityNotFoundException;
import rayan.userservice.dto.user.UserReadOnlyDTO;
import rayan.userservice.service.UserService;
import rayan.userservice.service.UserServiceImp;

import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;


    @GET
    public Response getAllUsers() {
        List<UserReadOnlyDTO> users = userService.getAllUsers();
        return Response.ok(users).entity(users).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) throws EntityNotFoundException {
        UserReadOnlyDTO userReadOnlyDTO = userService.getUserById(id);
        return Response.ok(userReadOnlyDTO)
                .entity(userReadOnlyDTO)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) throws EntityNotFoundException {
        userService.deleteUserById(id);
        return Response.ok().build();
    }
}
