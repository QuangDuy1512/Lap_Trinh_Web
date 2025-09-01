package Service;
import Model.User;

public interface UserService {
	void insert(User user);
	boolean register(String email, String password, String username, String
	fullname, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	User get(String username);
	User login(String username, String password);
}
