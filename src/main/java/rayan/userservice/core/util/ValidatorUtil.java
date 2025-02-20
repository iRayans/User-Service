package rayan.userservice.core.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rayan.userservice.core.excpetion.EntityAlreadyExistsException;
import rayan.userservice.core.excpetion.EntityInvalidArgumentsException;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.service.UserServiceImp;

@ApplicationScoped
public class ValidatorUtil {

    private static final Logger LOGGER = LogManager.getLogger(ValidatorUtil.class.getName());

    @Inject
    UserServiceImp userServiceImp;

    public void validateDTO(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException, EntityInvalidArgumentsException {
       if(userServiceImp.isEmailExist(userInsertDTO.getEmail())) {
           LOGGER.error("Email {} already exists", userInsertDTO.getEmail());
           throw new EntityAlreadyExistsException("User ", "User with Email: " + userInsertDTO.getEmail() + " is Already exist");
       } else if (!userInsertDTO.getPassword().equals(userInsertDTO.getConfirmPassword())) {
           LOGGER.error("Password does not match");
           throw new EntityInvalidArgumentsException("User ", "Password and confirm Password do not match!");
       }
    }
}
