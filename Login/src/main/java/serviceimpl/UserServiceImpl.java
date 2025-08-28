package serviceimpl;

import daoimpl.UserDaoImpl;
import model.User;
import service.UserService;
import dao.UserDAO;
public class UserServiceImpl implements UserService {
    UserDAO userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }
}

