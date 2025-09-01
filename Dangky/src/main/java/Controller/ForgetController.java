package Controller;

import java.io.IOException;

import Model.User;
import Service.UserService;
import Service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/forget")
public class ForgetController {
	 	UserService service = new UserServiceImpl();

	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        req.getRequestDispatcher("/forget.jsp").forward(req, resp);
	    }
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        String username = req.getParameter("username");
	        String email = req.getParameter("email");

	        User user = service.get(username);
	        if (user != null && user.getEmail().equalsIgnoreCase(email)) {
	            req.setAttribute("password", user.getPassWord()); // demo: show trực tiếp
	        } else {
	            req.setAttribute("alert", "Thông tin không chính xác!");
	        }

	        req.getRequestDispatcher("/forget.jsp").forward(req, resp);
	    }
}
