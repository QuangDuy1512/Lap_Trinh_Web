package Controller;

import java.io.IOException;

import Service.UserServiceImpl;
import Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	 // Nếu path là "/" thì tự động chuyển hướng sang /register
        String path = req.getServletPath();
        if (path.equals("/")) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }
    	
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin"); // hoặc "/waiting" tuỳ dự án
            return;
        }

        // Check cookie (remember me)
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // dùng "username" hoặc Constant.COOKIE_REMEMBER nếu đã định nghĩa
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/admin"); // hoặc "/waiting"
                    return;
                }
            }
        }

        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email    = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone    = req.getParameter("phone");

        UserService service = new UserServiceImpl();
        String alertMsg = "";

        // Validate tối thiểu
        if (username == null || username.isEmpty() ||
            password == null || password.isEmpty() ||
            email == null || email.isEmpty()) {
            alertMsg = "Vui lòng nhập đầy đủ thông tin bắt buộc.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra trùng
        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if (phone != null && !phone.isEmpty() && service.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // Đăng ký
        boolean isSuccess = service.register(username, password, email, fullname, phone);
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "System error!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
