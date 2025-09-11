package vn.iotstar.dao;

import vn.iotstar.entity.User;

public interface UserDAO {
    User findById(int id);
    User findByUsername(String username);
    void save(User user);
    void update(User user);
    void delete(int id);
}
