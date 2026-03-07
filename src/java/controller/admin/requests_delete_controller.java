package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.AssetRequest;
import model.Role;
import model.dao.RoleDAO;
import model.User;
import model.dao.AssetRequestDAO;
import model.dao.UserDAO;
import model.dao.RoleDAO;
import utilities.Validate;

public class requests_delete_controller extends HttpServlet {
    
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
        
        String requestId = request.getParameter("requestId");
        if(utilities.Validate.validateRequestId(requestId)) {
            try {
                if(new AssetRequestDAO().isExist(requestId)) {
                    request.setAttribute("requestToEdit", requestId); // Đặt đối tượng user vào request
                    request.getRequestDispatcher("/WEB-INF/admin/requests_delete.jsp").forward(request, response);
                } 
                else {
                     // Xử lý khi không tìm thấy request
                    response.sendRedirect(request.getContextPath() + "/admin/requests");
                }
            }
            catch(Exception e) { // Bắt các lỗi khác có thể xảy ra trong DAO
               session.setAttribute("flash_error", "An error occurred while fetching user data: " + e.getMessage());
               response.sendRedirect(request.getContextPath() + "/admin/requests");
            }
        }
        else {
            // Xử lý khi không tìm thấy request
            response.sendRedirect(request.getContextPath() + "/admin/requests");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
             
        HttpSession session = request.getSession();
        
        // 1. Lấy dữ liệu
        String requestId = request.getParameter("requestToEdit");
        
        // 2. Kiểm tra tài khoản có tồn tại hay không
        if(!new AssetRequestDAO().isExist(requestId)) {
            session.setAttribute("flash_error", "Request does not exists");
            response.sendRedirect(request.getContextPath() + "/admin/requests");
            return;
        }
        
        // 3. Delete
        if (!new AssetRequestDAO().delete(requestId)) {
            session.setAttribute("flash_error", "Failed to delete request");
            response.sendRedirect(request.getContextPath() + "/admin/requests/delete?requestId=" + requestId);
            return; 
        }
        
        session.setAttribute("flash_success", "Request deleted successfully");
        response.sendRedirect(request.getContextPath() + "/admin/requests");
        
//        out.println("post");
    }
}
