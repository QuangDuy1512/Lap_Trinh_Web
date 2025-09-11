package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/user/my-categories", "/user/my-category/*"})
public class CategoryUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService;

    public void init() {
        categoryService = new CategoryServiceImpl(JPAConfig.getEntityManager());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleid() != 1) {
            response.sendRedirect(request.getContextPath() + "/access-denied");
            return;
        }

        if (path.contains("/user/my-categories")) {
            // Hiển thị danh sách categories của user
            List<Category> userCategories = categoryService.findByUserId(user.getId());
            request.setAttribute("userCategories", userCategories);
            request.getRequestDispatcher("/views/user/my-categories.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleid() != 1) {
            response.sendRedirect(request.getContextPath() + "/access-denied");
            return;
        }

        if (path.contains("/user/my-category/add")) {
            // Xử lý thêm category mới
            Category category = new Category();
            category.setName(request.getParameter("name"));
            category.setCode(request.getParameter("code"));
            category.setImages(request.getParameter("images"));
            category.setStatus(request.getParameter("status") != null);
            category.setUser(user);
            
            categoryService.insert(category);
            response.sendRedirect(request.getContextPath() + "/user/my-categories");
        }
    }
}