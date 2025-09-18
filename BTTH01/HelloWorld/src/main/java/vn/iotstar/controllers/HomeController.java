package vn.iotstar.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;


/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = {"/home","/trangchu"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy user từ session
		User user = (User) req.getSession().getAttribute("user");
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> categories;
		if (user != null) {
			int roleid = user.getRoleid();
			if (roleid == 1 || roleid == 3) { // user hoặc admin
				categories = categoryService.findAll();
			} else if (roleid == 2) { // manager
				categories = categoryService.findByUserId(user.getId());
			} else {
				categories = null;
			}
			// Đưa danh sách category vào request
			req.setAttribute("categories", categories);
		} else {
			// Nếu chưa đăng nhập, chuyển hướng về trang login
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		// Forward tới trang home chung (có thể tuỳ chỉnh theo role nếu cần)
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bước 1: Nhận tham số request
		String name = req.getParameter("ten");
		// Bước 2: Xử lý tham số
		// Bước 3: Phản hồi kết quả
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("Hello" + " " + name);
			out.close();
	}

}