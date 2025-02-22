package rayan.userservice.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rayan.userservice.dto.user.UserReadOnlyDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponseDTO {
    private String token;
    private UserReadOnlyDTO user;
}
