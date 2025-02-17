package rayan.userservice.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rayan.userservice.dao.UserDAO;
import rayan.userservice.dto.UserInsertDTO;
import rayan.userservice.dto.UserReadOnlyDTO;
import rayan.userservice.entity.User;
import rayan.userservice.mapper.Mapper;

@ApplicationScoped
public class UserService {

    @Inject
    UserDAO userDAO;
    @Inject
    Mapper mapper;

    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO){
        // Default Role "USER"
        if (userInsertDTO.getRole() == null){
            userInsertDTO.setRole("USER");
        }
        User user = mapper.mapToUser(userInsertDTO);

        return userDAO.create(user)
                .map(mapper::mapToUserReadOnlyDTO)
                .orElse(null);
    }
}
