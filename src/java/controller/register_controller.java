package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import model.dao.UserDAO;
import utilities.Validate;

public class register_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/register/register.jsp")
               .forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        // 1. Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Validate username, password
        if (Validate.validateUsername(username) && Validate.validatePassword(password)) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            request.getRequestDispatcher("/WEB-INF/register/register.jsp")
                   .forward(request, response);
            return;
        }
        
        // 3. Kiểm tra tài khoản đã tồn tại hay chưa bằng DAO
        UserDAO dao = new UserDAO();
        if(dao.isExist(username)) {
            out.println("Tài khoản đã tồn tại");
            return;
        }
        
//        // 4. Sai tài khoản
//        if (user == null) {
//            request.getRequestDispatcher("/WEB-INF/login/login.jsp")
//                   .forward(request, response);
//            return;
//        }
//        
//        // 5. Đúng → lưu session
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
//
//        // 6. Điều hướng theo role
//        if (user.role_id == 1) { // admin
//            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
//        } else { // user thường
//            response.sendRedirect(request.getContextPath() + "/home");
//        }
    }
}