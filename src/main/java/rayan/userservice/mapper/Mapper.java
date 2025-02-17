package rayan.userservice.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import rayan.userservice.core.enums.RoleType;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;
import rayan.userservice.entity.User;
import rayan.userservice.security.PasswordUtil;

import java.time.LocalDateTime;

@ApplicationScoped
public class Mapper {


    public User mapToUser(UserInsertDTO userInsertDTO) {
        return new User(null,
                userInsertDTO.getUsername(),
                userInsertDTO.getEmail(),
                PasswordUtil.encryptPassword(userInsertDTO.getPassword()),
                RoleType.valueOf(userInsertDTO.getRole()),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
