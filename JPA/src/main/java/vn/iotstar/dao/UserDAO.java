package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.User;
public interface UserDAO {
	  User findByUsernameAndPassword(String username, String password);
	  User findByUsername(String username);
	  User findById(int id);
	  List<User> findAll();
	  void insert(User user);
	  void update(User user);
	  void delete(int id);
	  List<User> findByIds(List<Integer> ids);
}