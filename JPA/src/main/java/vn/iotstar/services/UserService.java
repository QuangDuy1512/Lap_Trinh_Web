package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.User;

public interface UserService {
    User login(String username, String password);
    User findById(int id);
    void update(User user);
    void insert(User user);
    void delete(int id);
    
    // Thêm các phương thức mới cho phân quyền
    List<User> findManagedUsers(User manager);
    boolean canManageUser(User manager, User user);
}