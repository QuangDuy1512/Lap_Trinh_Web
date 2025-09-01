package Service;

import DAO.UserDaoImpl;
import DAO.UserDAO;
import Model.User;

public class UserServiceImpl implements UserService {
    UserDAO userDao = new UserDaoImpl();

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false; // tài khoản đã tồn tại
        }
        long millis = System.currentTimeMillis();   
        java.sql.Date date = new java.sql.Date(millis);

        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setEmail(email);
        user.setFullName(fullname);
        user.setPhone(phone);
        user.setAvatar(null);
        user.setRoleid(3); // mặc định là user thường
        user.setCreatedDate(date);

        userDao.insert(user);
        return true;
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }
    
    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }
}

