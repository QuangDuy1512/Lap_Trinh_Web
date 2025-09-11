package vn.iotstar.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        // Cho phép truy cập các trang public
        if (uri.endsWith("login.jsp") || uri.endsWith("login") || uri.contains("/assets/")) {
            chain.doFilter(request, response);
            return;
        }

        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect(contextPath + "/login.jsp");
            return;
        }

        int role = user.getRoleid();

        // Kiểm tra phân quyền cho views
        if (uri.startsWith(contextPath + "/views/user") && role != 1) {
            resp.sendRedirect(contextPath + "/views/access-denied.jsp");
            return;
        }

        if (uri.startsWith(contextPath + "/views/manager") && role != 2) {
            resp.sendRedirect(contextPath + "/views/access-denied.jsp");
            return;
        }

        if (uri.startsWith(contextPath + "/views/admin") && role != 3) {
            resp.sendRedirect(contextPath + "/views/access-denied.jsp");
            return;
        }

        // Kiểm tra phân quyền cho các URL API mới
        if (uri.startsWith(contextPath + "/user/") && role != 1) {
            resp.sendRedirect(contextPath + "/views/access-denied.jsp");
            return;
        }

        if (uri.startsWith(contextPath + "/manager/") && role != 2 && role != 3) {
            resp.sendRedirect(contextPath + "/views/access-denied.jsp");
            return;
        }

        if (uri.startsWith(contextPath + "/admin/") && role != 3) {
            resp.sendRedirect(contextPath + "/views/access-denied.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}