package vn.iotstar.controllers;

import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.dao.impl.UserDAOImpl;
import vn.iotstar.entity.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/profile/update")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024)
public class ProfileUpdateServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        // Upload file ảnh
        Part filePart = request.getPart("image");
        String fileName = filePart != null ? filePart.getSubmittedFileName() : null;

        String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String savedFile = null;
        if (fileName != null && !fileName.isEmpty()) {
            savedFile = System.currentTimeMillis() + "_" + fileName;
            filePart.write(uploadPath + File.separator + savedFile);
        }

        // Lấy user từ session (giả sử login đã set sẵn)
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            user.setFullname(fullname);
            user.setPhone(phone);
            if (savedFile != null) {
                user.setImage(savedFile);
            }

            UserDAO dao = new UserDAOImpl();
            dao.update(user);

            // cập nhật session
            session.setAttribute("user", user);
        }

        response.sendRedirect(request.getContextPath() + "/views/profile.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mục đích: kích hoạt Hibernate để tạo database & bảng nếu chưa có
        try {
            JPAConfig.getEntityManager(); // chỉ cần gọi là Hibernate sẽ chạy
            response.getWriter().println("Database & tables initialized successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Failed to initialize database: " + e.getMessage());
        }
    }

}
