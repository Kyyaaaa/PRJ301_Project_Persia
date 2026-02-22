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

public class users_delete_controller extends HttpServlet {
    
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
                    request.getRequestDispatcher("/WEB-INF/admin/users_delete.jsp").forward(request, response);
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
             
        // 1. Lấy dữ liệu
        String username = request.getParameter("userToEdit");
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        // 3. Kiểm tra tài khoản có tồn tại hay không
        if(!userDAO.isExist(username)) {
            session.setAttribute("flash_error", "Username không tồn tại");
            response.sendRedirect(request.getContextPath() + "/admin/users/create");
            return;
        }
        
        // 4. Không được xóa tài khoản đang dùng
        if (currentUser != null && currentUser.getUsername().equals(username)) {
            session.setAttribute("flash_error", "Bạn không thể xóa tài khoản đang sử dụng!");
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        }
        
        // 5. Delete tài khoản
        if (!userDAO.delete(username)) {
            session.setAttribute("flash_error", "Delete tài khoản thất bại");
            response.sendRedirect(request.getContextPath() + "/admin/users/edit?username=" + request.getParameter("userToEdit"));
            return; 
        }
        
        session.setAttribute("flash_success", "Delete user thành công");
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
