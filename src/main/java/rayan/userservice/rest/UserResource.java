package rayan.userservice.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rayan.userservice.dao.UserDAO;
import rayan.userservice.dto.UserInsertDTO;
import rayan.userservice.dto.UserReadOnlyDTO;
import rayan.userservice.entity.User;
import rayan.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;


    @POST
    @Transactional
    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) {
        return userService.createUser(userInsertDTO);
    }

//    @GET
//    public List<User> hello() {
//        return userDAO.findAll();
//    }
//
//    @GET
//    @Path("/{id}")
//    public Optional<User> getUser(@PathParam("id") Long id) {
//        return userDAO.findById(id);
//    }
//
//    @PUT
//    @Path("/{id}")
//    public User updateUser(@PathParam("id") Long id, User user) {
//        return userDAO.update(user);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public String deleteUser(@PathParam("id") Long id) {
//        userDAO.delete(id);
//        return "User with id " + id + " deleted";
//    }
}
