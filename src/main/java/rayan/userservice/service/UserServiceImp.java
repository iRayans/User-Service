package rayan.userservice.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.core.excpetion.EntityNotFoundException;
import rayan.userservice.dao.UserDAO;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;
import rayan.userservice.entity.User;
import rayan.userservice.mapper.Mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class UserServiceImp implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImp.class.getName());

    @Inject
    UserDAO userDAO;
    @Inject
    Mapper mapper;

    @Transactional
    @Override
    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws AppServerException {
        LOGGER.info("Creating user...");
        // Default Role "USER"
        if (userInsertDTO.getRole() == null) {
            userInsertDTO.setRole("USER");
        }
        User user = mapper.mapToUser(userInsertDTO);

        UserReadOnlyDTO userReadOnlyDTO = userDAO.create(user)
                .map(mapper::mapToUserReadOnlyDTO)
                .orElseThrow(() -> new AppServerException("User ", "User with email: " + userInsertDTO.getEmail() + "not inserted."));
        LOGGER.info("User {} created successfully", userReadOnlyDTO.getName());
        return userReadOnlyDTO;
    }


    @Override
    public boolean isEmailExist(String email) {
        LOGGER.info("Checking if email exists...");
        return userDAO.emailExists(email);
    }

    @Override
    public List<UserReadOnlyDTO> getAllUsers() {
        LOGGER.info("Retrieving all users...");
        return userDAO.findAll().stream().map(mapper::mapToUserReadOnlyDTO).collect(Collectors.toList());
    }

    @Override
    public UserReadOnlyDTO getUserById(Long id) throws EntityNotFoundException {
        LOGGER.info("Retrieving user by id...");
        return userDAO.findById(id)
                .map(mapper::mapToUserReadOnlyDTO)
                .orElseThrow(() -> new EntityNotFoundException("User", "User with id " + id + " was not found."));
    }

    @Override
    public void deleteUserById(Long id) throws EntityNotFoundException {
        LOGGER.info("Deleting user by id...");
        if(!userDAO.existsById(id)) {
            throw new EntityNotFoundException("User", "User with id " + id + " was not found.");
        }
        userDAO.delete(id);
    }

}
