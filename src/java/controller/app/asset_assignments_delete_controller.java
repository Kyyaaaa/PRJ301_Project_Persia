package controller.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.View.AssetView;
import model.dao.AssetDAO;

public class asset_assignments_delete_controller extends HttpServlet {
    
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
//        if(utilities.Validate.validateUsername(username)) {
//            try {
//                if(userDAO.isExist(username)) {
//                    request.setAttribute("userToEdit", username); // Đặt đối tượng user vào request
//                    request.getRequestDispatcher("/WEB-INF/admin/users_delete.jsp").forward(request, response);
//                } 
//                else {
//                     // Xử lý khi không tìm thấy user
//                    response.sendRedirect(request.getContextPath() + "/admin/users");
//                }
//            }
//            catch(Exception e) { // Bắt các lỗi khác có thể xảy ra trong DAO
//               session.setAttribute("flash_error", "An error occurred while fetching user data: " + e.getMessage());
//               response.sendRedirect(request.getContextPath() + "/admin/users");
//            }
//        }
        
//        request.getRequestDispatcher("/WEB-INF/app/asset-assignments_delete.jsp")
//               .forward(request, response);
        
//        out.println("Get");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        
    }
}
