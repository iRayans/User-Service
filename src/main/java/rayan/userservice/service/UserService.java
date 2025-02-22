package rayan.userservice.service;

import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.core.excpetion.EntityNotFoundException;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;

import java.util.List;

public interface UserService {

    UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws AppServerException;

    boolean isEmailExist(String email);

    List<UserReadOnlyDTO> getAllUsers();

    UserReadOnlyDTO getUserById(Long id) throws EntityNotFoundException;

    void deleteUserById(Long id) throws EntityNotFoundException;

    boolean isUserValid(String email, String plainPassword);

    UserReadOnlyDTO findUserByEmail(String email) throws EntityNotFoundException;
}
