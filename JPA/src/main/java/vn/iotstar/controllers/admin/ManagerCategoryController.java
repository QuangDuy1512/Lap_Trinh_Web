package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.dao.ManagerUserDao;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.dao.impl.ManagerUserDaoImpl;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {
    "/manager/legacy/categories",  // Thay đổi URL pattern
    "/manager/legacy/category/*"   // Sử dụng wildcard để match tất cả các sub-paths
})
public class ManagerCategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();
    private ManagerUserDao managerUserDao = new ManagerUserDaoImpl();
    private UserDAO userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getServletPath();
        HttpSession session = req.getSession(false);
        User manager = (session != null) ? (User) session.getAttribute("user") : null;

        if (manager == null || manager.getRoleid() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        List<Integer> managedUserIds = managerUserDao.getUserIdsByManagerId(manager.getId());

        switch (url) {
            case "/manager/legacy/categories":
                List<Category> categories = cateService.findByUserIds(managedUserIds);
                req.setAttribute("listcate", categories);
                req.getRequestDispatcher("/views/manager/categories.jsp").forward(req, resp);
                break;

            case "/manager/legacy/category/create":
                List<User> managedUsers = userDao.findByIds(managedUserIds);
                req.setAttribute("managedUsers", managedUsers);
                req.setAttribute("action", req.getContextPath() + "/manager/legacy/category/store");
                req.getRequestDispatcher("/views/manager/category-form.jsp").forward(req, resp);
                break;

            case "/manager/legacy/category/edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                Category cateToEdit = cateService.findById(editId);
                if (cateToEdit != null && managedUserIds.contains(cateToEdit.getUser().getId())) {
                    req.setAttribute("category", cateToEdit);
                    req.setAttribute("action", req.getContextPath() + "/manager/legacy/category/update");
                    req.getRequestDispatcher("/views/manager/category-form.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/views/access-denied.jsp");
                }
                break;

            case "/manager/legacy/category/delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                Category cateToDelete = cateService.findById(deleteId);
                if (cateToDelete != null && managedUserIds.contains(cateToDelete.getUser().getId())) {
                    cateService.delete(deleteId);
                }
                resp.sendRedirect(req.getContextPath() + "/manager/legacy/categories");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getServletPath();
        HttpSession session = req.getSession(false);
        User manager = (session != null) ? (User) session.getAttribute("user") : null;

        if (manager == null || manager.getRoleid() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        List<Integer> managedUserIds = managerUserDao.getUserIdsByManagerId(manager.getId());

        switch (url) {
            case "/manager/legacy/category/store":
                String name = req.getParameter("categoryname");
                int userId = Integer.parseInt(req.getParameter("userId"));
                if (managedUserIds.contains(userId)) {
                    User owner = userDao.findById(userId);
                    Category newCate = new Category();
                    newCate.setName(name);
                    newCate.setUser(owner);
                    cateService.insert(newCate);
                }
                break;

            case "/manager/legacy/category/update":
                int id = Integer.parseInt(req.getParameter("id"));
                String newName = req.getParameter("categoryname");
                Category cateToUpdate = cateService.findById(id);
                if (cateToUpdate != null && managedUserIds.contains(cateToUpdate.getUser().getId())) {
                    cateToUpdate.setName(newName);
                    cateService.update(cateToUpdate);
                }
                break;
        }
        resp.sendRedirect(req.getContextPath() + "/manager/legacy/categories");
    }
}