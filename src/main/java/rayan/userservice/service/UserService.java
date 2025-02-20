package rayan.userservice.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.dao.UserRepository;
import rayan.userservice.dao.UserRepositoryImpl;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;
import rayan.userservice.entity.User;
import rayan.userservice.mapper.Mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@ApplicationScoped
public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());

    @Inject
    UserRepository userRepo;
    @Inject
    Mapper mapper;

    @Transactional
    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws AppServerException {
        LOGGER.info("Creating user...");
        // Default Role "USER"
        if (userInsertDTO.getRole() == null) {
            userInsertDTO.setRole("USER");
        }
        User user = mapper.mapToUser(userInsertDTO);

        UserReadOnlyDTO userReadOnlyDTO = userRepo.create(user)
                .map(mapper::mapToUserReadOnlyDTO)
                .orElseThrow(() -> new AppServerException("User ", "User with email: " + userInsertDTO.getEmail() + "not inserted."));
        LOGGER.info("User {} created successfully", userReadOnlyDTO.getName());
        return userReadOnlyDTO;
    }

    public boolean isEmailExist(String email) {
        return userRepo.emailExists(email);
    }
}
