package rayan.userservice.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.core.excpetion.EntityAlreadyExistsException;
import rayan.userservice.core.excpetion.EntityInvalidArgumentsException;
import rayan.userservice.core.excpetion.EntityNotFoundException;
import rayan.userservice.core.util.ValidatorUtil;
import rayan.userservice.dto.authentication.AuthenticationProvider;
import rayan.userservice.dto.authentication.AuthenticationResponseDTO;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserLoginDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;
import rayan.userservice.security.JWTService;
import rayan.userservice.service.UserService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationResource.class.getName());

    @Inject
    UserService userServiceImp;
    @Inject
    ValidatorUtil validatorUtil;
    @Inject
    AuthenticationProvider authenticationProvider;
    @Inject
    JWTService jwtService;


    @POST
    @Path("/register")
    public Response registerUser(UserInsertDTO userInsertDTO)
            throws AppServerException, EntityInvalidArgumentsException, EntityAlreadyExistsException {
        // Validation
        validatorUtil.validateDTO(userInsertDTO);

        UserReadOnlyDTO userReadOnlyDTO = userServiceImp.createUser(userInsertDTO);
        return Response.status(Response.Status.CREATED)
                .entity(userReadOnlyDTO)
                .build();
    }

    @POST
    @Path("/login")
    public Response loginUser(UserLoginDTO userLoginDTO) throws EntityInvalidArgumentsException, EntityNotFoundException {
        // Authentication
        boolean isAuthenticated = authenticationProvider.authenticate(userLoginDTO);
        if (!isAuthenticated) {
            LOGGER.error("email or password incorrect");
            throw new EntityInvalidArgumentsException("User", "email or password incorrect");
        }

        UserReadOnlyDTO userReadOnlyDTO = userServiceImp.findUserByEmail(userLoginDTO.getEmail());
        String username = userReadOnlyDTO.getName();
        String role = userReadOnlyDTO.getRole();
        // Create a JWT token for current user
        String token = jwtService.generateToken(userLoginDTO.getEmail(), username, role);
        UserReadOnlyDTO user = userServiceImp.findUserByEmail(userLoginDTO.getEmail());
        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO(token, user);

        LOGGER.info("Logged in user {} ", userLoginDTO.getEmail());
        return Response.status(Response.Status.OK).entity(authenticationResponseDTO).build();
    }

}
