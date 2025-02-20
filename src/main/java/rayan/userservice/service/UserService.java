package rayan.userservice.service;

import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.dto.user.UserInsertDTO;
import rayan.userservice.dto.user.UserReadOnlyDTO;

public interface UserService {

    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws AppServerException;
    public boolean isEmailExist(String email);
}
