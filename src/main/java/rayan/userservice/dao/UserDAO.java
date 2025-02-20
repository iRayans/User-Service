package rayan.userservice.dao;

import rayan.userservice.core.excpetion.AppServerException;
import rayan.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<User> create(User user) throws AppServerException;
    List<User> findAll();
    Optional<User> findById(Long id);
    void delete(Long id);
    User update(User user);
    boolean emailExists(String email);
}
