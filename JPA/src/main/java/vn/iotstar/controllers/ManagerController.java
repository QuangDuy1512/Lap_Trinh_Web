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
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/manager/users", "/manager/categories/*"})
public class ManagerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService;
    private UserService userService;

    public void init() {
        categoryService = new CategoryServiceImpl(JPAConfig.getEntityManager());
        userService = new UserServiceImpl(JPAConfig.getEntityManager());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        User manager = (User) session.getAttribute("user");

        if (manager == null || manager.getRoleid() != 2) {
            response.sendRedirect(request.getContextPath() + "/access-denied");
            return;
        }

        if (path.contains("/manager/users")) {
            // Lấy danh sách user do manager quản lý
            List<User> managedUsers = userService.findManagedUsers(manager);
            request.setAttribute("managedUsers", managedUsers);

            // Nếu có userId được chọn, lấy categories của user đó
            String userIdStr = request.getParameter("userId");
            if (userIdStr != null) {
                int userId = Integer.parseInt(userIdStr);
                User selectedUser = userService.findById(userId);
                
                if (selectedUser != null && userService.canManageUser(manager, selectedUser)) {
                    request.setAttribute("selectedUser", selectedUser);
                    List<Category> userCategories = categoryService.findByUserId(selectedUser.getId());
                    request.setAttribute("userCategories", userCategories);
                }
            }

            request.getRequestDispatcher("/views/manager/managed-users.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        User manager = (User) session.getAttribute("user");

        if (manager == null || manager.getRoleid() != 2) {
            response.sendRedirect(request.getContextPath() + "/access-denied");
            return;
        }

        if (path.contains("/manager/categories/add")) {
            // Xử lý thêm category mới cho user được quản lý
            String userIdStr = request.getParameter("userId");
            if (userIdStr != null) {
                int userId = Integer.parseInt(userIdStr);
                User selectedUser = userService.findById(userId);
                
                if (selectedUser != null && userService.canManageUser(manager, selectedUser)) {
                    Category category = new Category();
                    category.setName(request.getParameter("name"));
                    category.setCode(request.getParameter("code"));
                    category.setImages(request.getParameter("images"));
                    category.setStatus(request.getParameter("status") != null);
                    category.setUser(selectedUser);
                    
                    categoryService.insert(category);
                }
            }
            response.sendRedirect(request.getContextPath() + "/manager/users?userId=" + userIdStr);
        }
    }
}