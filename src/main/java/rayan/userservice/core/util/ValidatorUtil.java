package rayan.userservice.core.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import rayan.userservice.core.excpetion.EntityAlreadyExistsException;
import rayan.userservice.core.excpetion.EntityInvalidArgumentsException;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.service.UserService;

@ApplicationScoped
public class ValidatorUtil {

    @Inject
    UserService userService;

    public void validateDTO(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException, EntityInvalidArgumentsException {
       if(userService.isEmailExist(userInsertDTO.getEmail())) {
           throw new EntityAlreadyExistsException("User ", "User with Email: " + userInsertDTO.getEmail() + " is Already exist");
       } else if (!userInsertDTO.getPassword().equals(userInsertDTO.getConfirmPassword())) {
           throw new EntityInvalidArgumentsException("User ", "Password and confirm Password do not match!");
       }
    }
}
