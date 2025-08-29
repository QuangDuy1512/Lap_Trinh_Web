package DAO;

import Model.User;

public interface UserDAO {
    void insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}

