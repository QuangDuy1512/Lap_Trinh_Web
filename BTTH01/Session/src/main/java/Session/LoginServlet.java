package Session;



import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns= {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
    	// lấy dữ liệu từ tham số của form
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	PrintWriter out = resp.getWriter();
    	if (username.equals("hao")&& password.equals("123")) {
    	out.print("Chào mừng bạn, " + username);
    	HttpSession session = req.getSession();
    	session.setAttribute("name", username);
    	} else {
    	out.print("Tài khoản hoặc mật khẩu không chính xác");
    	req.getRequestDispatcher("Login.html").include(req,
    	resp);
    	}
    }

}
