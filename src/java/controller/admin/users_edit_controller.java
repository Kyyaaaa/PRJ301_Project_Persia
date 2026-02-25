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

public class users_edit_controller extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO(); 
    
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
        
        String username = request.getParameter("username");
        if(utilities.Validate.validateUsername(username)) {
            try {
                if(userDAO.isExist(username)) {
                    request.setAttribute("userToEdit", username); // Đặt đối tượng user vào request
                    request.getRequestDispatcher("/WEB-INF/admin/users_edit.jsp").forward(request, response);
                } 
                else {
                     // Xử lý khi không tìm thấy user
                    response.sendRedirect(request.getContextPath() + "/admin/users");
                }
            }
            catch(Exception e) { // Bắt các lỗi khác có thể xảy ra trong DAO
               session.setAttribute("flash_error", "An error occurred while fetching user data: " + e.getMessage());
               response.sendRedirect(request.getContextPath() + "/admin/users");
            }

        }
        
//        out.println(username);
//        out.println(userDAO.isExist(username));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
             
        // 1. Lấy dữ liệu từ form
        String username = request.getParameter("userToEdit");
        String password = request.getParameter("password");
        String role_id = request.getParameter("role_id");
        
        HttpSession session = request.getSession();
        
        // 2. Validate username, password
        if (!Validate.validateUsername(username) || !Validate.validatePassword(password) ||
                !Validate.validateRoleId(role_id)
                ) {   
            session.setAttribute("flash_error", "Invalid Password / Role");
//            out.println("Sai format r thang ngu");
//            out.println(request.getParameter("userToEdit"));
            response.sendRedirect(request.getContextPath() + "/admin/users/edit?username=" + request.getParameter("userToEdit"));
            return;
        }
        
//            out.println(username);
//            out.println("<br>");
//            out.println(password);
//            out.println("<br>");
//            out.println(role_id);
        
        // 3. Kiểm tra tài khoản có tồn tại hay không
        if(!userDAO.isExist(username)) {
            session.setAttribute("flash_error", "Username does not exist");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }
        
        // 4. Update tài khoản
        if (!userDAO.update(username, password, Integer.parseInt(role_id))) {
            session.setAttribute("flash_error", "Failed to update user");
            response.sendRedirect(request.getContextPath() + "/admin/users/edit?username=" + request.getParameter("userToEdit"));
            return; 
        }
        
        session.setAttribute("flash_success", "User updated successfully");
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
