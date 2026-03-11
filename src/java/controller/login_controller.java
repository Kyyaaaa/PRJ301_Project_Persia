package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import model.dao.UserDAO;
import utilities.Validate;

public class login_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        // Return if session has logged in user
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (user.getRole_id() == 1) {
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                } else {
                    response.sendRedirect(request.getContextPath() + "/app/dashboard");
                }
                return;
            }
        }
        
        // Lấy flash error từ session (nếu có)
        if (session != null) {
            String error = (String) session.getAttribute("flash_error");
            if (error != null) {
                request.setAttribute("error", error);
                session.removeAttribute("flash_error");
            }
        }
        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/login/login.jsp")
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
        
        HttpSession session = request.getSession();
        
        // 2. Validate username, password
        if (!Validate.validateUsername(username) || !Validate.validatePassword(password)) {
            session.setAttribute("flash_error", "Username / Password không hợp lệ");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 3. Kiểm tra tài khoản bằng DAO
        UserDAO dao = new UserDAO();
        User user = dao.login(username, password);
        
        // 4. Sai tài khoản
        if (user == null) {
            session.setAttribute("flash_error", "Sai Username / Password");
            response.sendRedirect(request.getContextPath() + "/login");
            return; 
        }
        
        // 5. Đúng → lưu user
        session.setAttribute("user", user);

        // 6. Điều hướng theo role
        if (user.getRole_id() == 1) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/app/dashboard");
        }
    }
}