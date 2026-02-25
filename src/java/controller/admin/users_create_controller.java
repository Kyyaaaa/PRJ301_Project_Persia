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
import model.dao.RoleDAO;
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
        
        List<Role> list_role = new RoleDAO().getAllRoles();
        request.setAttribute("list_role", list_role);
        
//        out.println("Hi");
//        for(Role i : list_role) {
//            out.println(i.role_id + " " + i.role_name);
//            out.println("<br>");
//        }
        
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
        if (!Validate.validateUsername(username)) {   
            session.setAttribute("flash_error", "Username must be between 3 and 30 characters long and must not contain special characters");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }
        if (!Validate.validatePassword(password)) {   
            session.setAttribute("flash_error", "Password must be between 3 and 12 characters long and must not contain special characters");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }
        if (!Validate.validateRoleId(role_id)) {   
            session.setAttribute("flash_error", "Invalid role");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }

        // 3. Kiểm tra tài khoản đã tồn tại hay chưa bằng DAO
        UserDAO dao = new UserDAO();
        if(dao.isExist(username)) {
            session.setAttribute("flash_error", "Username already exists");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }
        
        // 4. Tạo tài khoản mới
        User user = dao.register(username, password, Integer.parseInt(role_id));

        if (user == null) {
            session.setAttribute("flash_error", "Failed to create account");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return; 
        }
        
        // 5. Hiện thông báo và quay về /admin/users
        session.setAttribute("flash_success", "User created successfully");
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
