package Controller;import java.io.IOException;
import java.util.List;
import DAO.CategoryDAO;
import DAO.CategoryDAOImpl;
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

@WebServlet("/edit")
public class CategoryEditController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category cate = cateService.get(id);
        req.setAttribute("cate", cate);
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String icon = req.getParameter("icon");

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("account");

        Category cate = new Category(id, name, icon, user.getID());
        cateService.edit(cate);

        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
