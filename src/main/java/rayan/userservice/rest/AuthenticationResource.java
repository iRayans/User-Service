package rayan.userservice.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.core.excpetion.EntityAlreadyExistsException;
import rayan.userservice.core.excpetion.EntityInvalidArgumentsException;
import rayan.userservice.core.util.ValidatorUtil;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;
import rayan.userservice.service.UserService;

@Path("/auth")
public class AuthenticationResource {

    @Inject
    UserService userServiceImp;
    @Inject
    ValidatorUtil validatorUtil;
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(UserInsertDTO userInsertDTO)
            throws AppServerException, EntityInvalidArgumentsException, EntityAlreadyExistsException {
        // Validation
        validatorUtil.validateDTO(userInsertDTO);

        UserReadOnlyDTO userReadOnlyDTO = userServiceImp.createUser(userInsertDTO);
        return Response.status(Response.Status.CREATED)
                .entity(userReadOnlyDTO)
                .build();
    }
}
