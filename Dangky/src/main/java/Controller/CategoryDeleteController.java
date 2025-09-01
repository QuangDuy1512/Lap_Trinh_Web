package Controller;

import Service.CategoryServiceImpl;

import java.io.IOException;

import Service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class CategoryDeleteController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        cateService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
