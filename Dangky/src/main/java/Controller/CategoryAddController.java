package Controller;

import java.io.IOException;

import Model.Category;
import Model.User;
import Service.CategoryServiceImpl;
import Service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add")
public class CategoryAddController extends HttpServlet  {
   
	CategoryService cateService = new CategoryServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Hiển thị form add
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
    

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String icon = req.getParameter("icon");

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("account");

        Category cate = new Category();
        cate.setName(name);
        cate.setIcon(icon);
        cate.setUserId(user.getID());

        cateService.insert(cate);

        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
