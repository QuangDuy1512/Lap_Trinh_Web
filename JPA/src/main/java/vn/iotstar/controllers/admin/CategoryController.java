package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/create",
        "/admin/category/store",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete"
})
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();

        if (url.equals("/admin/categories")) {
            List<Category> listCategory = cateService.findAll();
            req.setAttribute("listcate", listCategory);
            req.getRequestDispatcher("/views/admin/categories.jsp").forward(req, resp);

        } else if (url.equals("/admin/category/create")) {
            req.getRequestDispatcher("/views/admin/category-form.jsp").forward(req, resp);

        } else if (url.equals("/admin/category/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category cate = cateService.findById(id);
            req.setAttribute("category", cate);
            req.getRequestDispatcher("/views/admin/category-form.jsp").forward(req, resp);

        } else if (url.equals("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            cateService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();

        if (url.equals("/admin/category/store")) {
            String name = req.getParameter("categoryname");
            Category cate = new Category();
            cate.setCategoryname(name);
            cateService.insert(cate);

        } else if (url.equals("/admin/category/update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("categoryname");
            Category cate = new Category(id, name);
            cateService.update(cate);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }
}

