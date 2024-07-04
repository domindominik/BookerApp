package aitt.bookerapp.service;

import aitt.bookerapp.model.UserModel;
import java.util.List;

public interface UserService {
    List<UserModel> getAllUsers();
    UserModel getUserById(Long id);
    UserModel saveUser(UserModel user);
    void deleteUserById(Long id);
    boolean existsById(Long id);
}
