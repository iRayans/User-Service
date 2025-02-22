package rayan.userservice.dto.authentication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import rayan.userservice.dto.user.UserLoginDTO;
import rayan.userservice.service.UserService;

@ApplicationScoped
@NoArgsConstructor
public class AuthenticationProvider {

    @Inject
    UserService userService;


    public boolean authenticate(UserLoginDTO userLoginDTO) {
        return userService.isUserValid(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }
}
