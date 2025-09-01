package Controller;

import java.io.IOException;
import java.util.List;
import DAO.CategoryDAO;
import DAO.CategoryDAOImpl;
import Model.Category;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/list")
public class CategoryListController extends HttpServlet  {
	
	CategoryDAO categoryDAO = new CategoryDAOImpl();
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("account");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<Category> cateList = categoryDAO.getAllByUser(user.getID());
        req.setAttribute("cateList", cateList);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
