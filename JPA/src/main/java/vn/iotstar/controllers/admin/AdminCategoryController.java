package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {
    "/admin/legacy/categories",  // Thay đổi URL pattern
    "/admin/legacy/category/*"   // Sử dụng wildcard để match tất cả các sub-paths
})
public class AdminCategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getServletPath();
        String pathInfo = req.getPathInfo();

        if (url.equals("/admin/legacy/categories")) {
            List<Category> list = cateService.findAll();
            req.setAttribute("categories", list);
            req.getRequestDispatcher("/views/admin/categories.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        }
    }
}