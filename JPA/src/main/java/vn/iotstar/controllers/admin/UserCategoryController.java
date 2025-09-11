package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {
    "/user/legacy/categories",  // Thay đổi URL pattern
    "/user/legacy/category/*"   // Sử dụng wildcard để match tất cả các sub-paths
})
public class UserCategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getServletPath();
        User user = (User) req.getSession().getAttribute("user");

        if (user == null || user.getRoleid() != 1) {
            resp.sendRedirect(req.getContextPath() + "/views/access-denied.jsp");
            return;
        }

        switch (url) {
            case "/user/legacy/categories":
                List<Category> categories = cateService.findByUserId(user.getId());
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/views/user/categories.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/user/home");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getServletPath();
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        switch (url) {
            case "/user/category/store":
                String name = req.getParameter("categoryname");
                Category newCate = new Category();
                newCate.setName(name);
                newCate.setUser(user);
                cateService.insert(newCate);
                break;

            case "/user/category/update":
                int id = Integer.parseInt(req.getParameter("id"));
                String newName = req.getParameter("categoryname");
                Category cate = cateService.findById(id);
                if (cate != null && cate.getUser().getId() == user.getId()) {
                    cate.setName(newName);
                    cateService.update(cate);
                }
                break;
        }

        // luôn redirect về danh sách để load dữ liệu mới nhất
        resp.sendRedirect(req.getContextPath() + "/user/categories");
    }
}