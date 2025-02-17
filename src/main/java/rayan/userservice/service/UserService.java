package rayan.userservice.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.dao.UserDAO;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;
import rayan.userservice.entity.User;
import rayan.userservice.mapper.Mapper;

@ApplicationScoped
public class UserService {

    @Inject
    UserDAO userDAO;
    @Inject
    Mapper mapper;

    @Transactional
    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws AppServerException {
        // Default Role "USER"
        if (userInsertDTO.getRole() == null){
            userInsertDTO.setRole("USER");
        }
        User user = mapper.mapToUser(userInsertDTO);

        return userDAO.create(user)
                .map(mapper::mapToUserReadOnlyDTO)
                .orElseThrow(() -> new AppServerException("User ", "User with email: " + userInsertDTO.getEmail() + "not inserted."));
    }

    public boolean isEmailExist(String email){
        return userDAO.emailExists(email);
    }
}
