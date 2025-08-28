package dao;

import model.User;

public interface UserDAO {
	  User get(String username);
}
