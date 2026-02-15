package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Role;
import model.dao.RoleDAO;
import model.User;
import model.dao.UserDAO;
import utilities.Validate;

public class users_create_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            String error = (String) session.getAttribute("flash_error");
            if (error != null) {
                request.setAttribute("error", error);
                session.removeAttribute("flash_error");
            }
        }
        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/users_create.jsp")
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
        String role_id = request.getParameter("role_id");
        
        HttpSession session = request.getSession();
        
        // 2. Validate username, password
        if (!Validate.validateUsername(username) || !Validate.validatePassword(password) ||
                role_id == null || (!role_id.equals("1") && !role_id.equals("2") && !role_id.equals("3"))
                ) {   
            session.setAttribute("flash_error", "Username / Password / Role không hợp lệ");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }

        // 3. Kiểm tra tài khoản đã tồn tại hay chưa bằng DAO
        UserDAO dao = new UserDAO();
        if(dao.isExist(username)) {
            session.setAttribute("flash_error", "Username đã tồn tại");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }
        
        // 4. Tạo tài khoản mới
        User user = dao.register(username, password, Integer.parseInt(role_id));

        if (user == null) {
            session.setAttribute("flash_error", "Tạo tài khoản thất bại");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return; 
        }
        
        // 5. Hiện thông báo và quay về /admin/users
        session.setAttribute("flash_success", "Tạo user thành công");
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
