package vn.iotstar.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
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

@WebServlet(urlPatterns = {
    "/home", 
    "/trangchu",
    "/admin/home",
    "/admin/category",
    "/admin/category/*",
    "/manager/home",
    "/manager/category",
    "/manager/category/*",
    "/user/home",
    "/user/category",        // Sửa lại URL cho user
    "/user/category/*"       // Sửa lại URL cho user
})
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService;
    private UserService userService;
    private ObjectMapper objectMapper;
    private EntityManager entityManager;
    
    @Override
    public void init() throws ServletException {
        entityManager = JPAConfig.getEntityManager();
        categoryService = new CategoryServiceImpl(entityManager);
        userService = new UserServiceImpl(entityManager);
        objectMapper = new ObjectMapper();
    }
    
    @Override
    public void destroy() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        super.destroy();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String path = req.getServletPath();
        String pathInfo = req.getPathInfo();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            // Xử lý các request theo role
            switch (path) {
                case "/home":
                case "/trangchu":
                    handleHome(req, resp, user);
                    break;

                case "/admin/home":
                    if (!isAdmin(user)) {
                        resp.sendRedirect(req.getContextPath() + "/views/access-denied.jsp");
                        return;
                    }
                    handleAdminHome(req, resp);
                    break;
                    
                case "/manager/home":
                    if (!isManager(user)) {
                        resp.sendRedirect(req.getContextPath() + "/views/access-denied.jsp");
                        return;
                    }
                    handleManagerHome(req, resp);
                    break;
                    
                case "/user/home":
                    if (!isUser(user)) {
                        resp.sendRedirect(req.getContextPath() + "/views/access-denied.jsp");
                        return;
                    }
                    handleUserHome(req, resp);
                    break;
                    
                case "/admin/category":
                case "/manager/category":
                case "/user/category":
                    handleCategoryOperations(req, resp, user, pathInfo);
                    break;
                    
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    private void handleHome(HttpServletRequest req, HttpServletResponse resp, User user) 
            throws ServletException, IOException {
        switch (user.getRoleid()) {
            case 3: // Admin
                resp.sendRedirect(req.getContextPath() + "/admin/home");
                break;
            case 2: // Manager
                resp.sendRedirect(req.getContextPath() + "/manager/home");
                break;
            case 1: // User
                resp.sendRedirect(req.getContextPath() + "/user/home");
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/views/access-denied.jsp");
        }
    }

    private void handleUserHome(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        // Chỉ lấy categories của user hiện tại
        List<Category> categories = categoryService.findByUserId(user.getId());
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/admin/categories.jsp").forward(req, resp);
    }

    private void handleManagerHome(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        User manager = (User) req.getSession().getAttribute("user");
        try {
            // Lấy categories của các user do manager quản lý
            List<Category> categories = categoryService.findByManagedUsers(manager);
            // Lấy danh sách user do manager quản lý để hiển thị trong modal assign
            List<User> managedUsers = userService.findManagedUsers(manager);
            
            req.setAttribute("categories", categories);
            req.setAttribute("managedUsers", managedUsers);
            req.getRequestDispatcher("/views/admin/categories.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading data: " + e.getMessage());
        }
    }

    private void handleAdminHome(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Admin có thể xem tất cả categories
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/admin/categories.jsp").forward(req, resp);
    }
    
    private void handleCategoryOperations(HttpServletRequest req, HttpServletResponse resp, User user, String pathInfo) 
            throws ServletException, IOException {
        // Kiểm tra quyền truy cập
        if (!isAdmin(user) && !isManager(user)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (pathInfo == null || pathInfo.equals("/")) {
            // Get category list
            List<Category> categories;
            if (isAdmin(user)) {
                categories = categoryService.findAll();
            } else {
                categories = categoryService.findByUserId(user.getId());
            }
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getOutputStream(), categories);
            
        } else if (pathInfo.matches("/\\d+")) {
            // Get single category
            int categoryId = Integer.parseInt(pathInfo.substring(1));
            Category category = categoryService.findById(categoryId);
            
            if (category == null || (!isAdmin(user) && category.getUserid() != user.getId())) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getOutputStream(), category);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String path = req.getServletPath();
        String pathInfo = req.getPathInfo();
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            if (path.startsWith("/admin/category") || path.startsWith("/manager/category") || path.startsWith("/user/category")) {
                if (pathInfo != null && pathInfo.startsWith("/delete/")) {
                    // Xử lý xóa category
                    int categoryId = Integer.parseInt(pathInfo.substring("/delete/".length()));
                    Category category = categoryService.findById(categoryId);
                    
                    // Kiểm tra quyền xóa
                    if (category == null || !canModifyCategory(user, category)) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                    
                    categoryService.delete(categoryId);
                    
                    // Chuyển hướng về trang danh sách
                    String redirectUrl = req.getContextPath() + 
                        (user.getRoleid() == 3 ? "/admin/home" : 
                         user.getRoleid() == 2 ? "/manager/home" : "/user/home");
                    resp.sendRedirect(redirectUrl);
                    return;
                }
                
                handleCategoryPost(req, resp, user, path, pathInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleCategoryPost(HttpServletRequest req, HttpServletResponse resp, User user, String path, String pathInfo) 
            throws ServletException, IOException {
        // Kiểm tra quyền truy cập
        if (path.startsWith("/admin/") && !isAdmin(user)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (path.startsWith("/manager/") && !isManager(user)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (pathInfo == null || pathInfo.equals("/add")) {
            // Thêm mới category
            Category category = new Category();
            updateCategoryFromRequest(category, req);
            category.setUser(user); // Gán user hiện tại là người tạo
            categoryService.insert(category);

        } else if (pathInfo.startsWith("/edit/")) {
            // Cập nhật category
            int categoryId = Integer.parseInt(pathInfo.substring("/edit/".length()));
            Category category = categoryService.findById(categoryId);

            if (category == null || !canModifyCategory(user, category)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            updateCategoryFromRequest(category, req);
            categoryService.update(category);

        } else if (pathInfo.equals("/assign") && isManager(user)) {
            // Xử lý gán category cho user (chỉ dành cho manager)
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            
            Category category = categoryService.findById(categoryId);
            User targetUser = userService.findById(userId);

            if (category == null || targetUser == null || !userService.canManageUser(user, targetUser)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            category.setUser(targetUser);
            categoryService.update(category);
        }

        // Redirect về trang chủ tương ứng
        String redirectPath = path.startsWith("/admin/") ? "/admin/home" :
                            path.startsWith("/manager/") ? "/manager/home" : "/user/home";
        resp.sendRedirect(req.getContextPath() + redirectPath);
    }

    private void updateCategoryFromRequest(Category category, HttpServletRequest req) {
        category.setName(req.getParameter("name"));
        category.setCode(req.getParameter("code"));
        category.setImages(req.getParameter("images"));
        category.setStatus(req.getParameter("status") != null);
    }

    private boolean canModifyCategory(User user, Category category) {
        if (isAdmin(user)) return true;
        if (isManager(user)) {
            return userService.canManageUser(user, category.getUser());
        }
        return user.getId() == category.getUser().getId();
    }

    private boolean isAdmin(User user) {
        return user != null && user.getRoleid() == 3;
    }
    
    private boolean isManager(User user) {
        return user != null && user.getRoleid() == 2;
    }
    
    private boolean isUser(User user) {
        return user != null && user.getRoleid() == 1;
    }
}
