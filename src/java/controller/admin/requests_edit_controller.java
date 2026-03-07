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

public class requests_edit_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            String success = (String) session.getAttribute("flash_success");
            if (success != null) {
                request.setAttribute("success", success);
                session.removeAttribute("flash_success");
            }
            
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
                    request.getRequestDispatcher("/WEB-INF/admin/requests_edit.jsp").forward(request, response);
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
        
//        out.println("get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
             
        HttpSession session = request.getSession();
        
        // 1. Lấy dữ liệu
        String status = request.getParameter("status");
        String reviewNote = request.getParameter("reviewNote");
        
        String requestId = request.getParameter("requestToEdit");
        User user = (User) session.getAttribute("user");
        
        // 2. Validate
        if (!Validate.validateAssetRequestStatus(status)) {
            session.setAttribute("flash_error", "Status must be either APPROVE / REJECTED");
            response.sendRedirect(request.getContextPath() + "/admin/requests/edit?requestId=" + requestId);
            return; 
        }
        if (!Validate.validateAssetRequestReviewNote(reviewNote)) {
            session.setAttribute("flash_error", "Review note must not be empty and must not exceed 255 characters");
            response.sendRedirect(request.getContextPath() + "/admin/requests/edit?requestId=" + requestId);
            return; 
        }
        if(!new AssetRequestDAO().isExist(requestId)) {
            session.setAttribute("flash_error", "Request does not exist");
            response.sendRedirect(request.getContextPath() + "/admin/requests/edit?requestId=" + requestId);
        }
        
        AssetRequest cur = new AssetRequestDAO().findById(Integer.parseInt(requestId));
        if(cur != null && !cur.status.equals("PENDING")) {
            session.setAttribute("flash_error", "This request is not pending");
            response.sendRedirect(request.getContextPath() + "/admin/requests");
            return;
        }
        
        // 3. Cập nhật
//        out.println(Integer.parseInt(requestId));
//        out.println("<br>");
//        out.println(status);
//        out.println("<br>");
//        out.println(user.username);
        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
//        out.println(now);
//        out.println("<br>");
//        out.println(reviewNote);

        AssetRequest tmp = new AssetRequestDAO().edit(Integer.parseInt(requestId), status, user.username, now, reviewNote);
        
        // 4. Hiện thông báo và quay về
        session.setAttribute("flash_success", "Request updated successfully");
        response.sendRedirect(request.getContextPath() + "/admin/requests");
    }
}
