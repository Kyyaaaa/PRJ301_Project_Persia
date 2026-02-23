package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import model.dao.AssetDAO;
import model.dao.RoleDAO;

public class assets_delete_controller extends HttpServlet {
    
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
        
        String assetId = request.getParameter("assetId");
        try {
            if(new AssetDAO().isExist(assetId)) {
                request.setAttribute("assetToEdit", assetId); // Đặt đối tượng user vào request
                request.getRequestDispatcher("/WEB-INF/admin/assets_delete.jsp").forward(request, response);
            } 
            else {
                 // Xử lý khi không tìm thấy asset
                response.sendRedirect(request.getContextPath() + "/admin/assets/read");
            }
        }
        catch(Exception e) { // Bắt các lỗi khác có thể xảy ra trong DAO
           session.setAttribute("flash_error", "An error occurred while fetching user data: " + e.getMessage());
           response.sendRedirect(request.getContextPath() + "/admin/assets/read");
        }
        
//        out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        // 1. Lấy dữ liệu
        String assetId = request.getParameter("assetToEdit");
//        out.println(assetId);
        
        HttpSession session = request.getSession();
        
        // 2. Kiểm tra asset có tồn tại hay không
        if(!new AssetDAO().isExist(assetId)) {
            session.setAttribute("flash_error", "Tài sản không tồn tại");
            response.sendRedirect(request.getContextPath() + "/admin/assets/read");
            return;
        }
        
        // 3. Delete tài khoản
        if (!new AssetDAO().delete(assetId)) {
            session.setAttribute("flash_error", "Delete tài sản thất bại");
            response.sendRedirect(request.getContextPath() + "/admin/assets/edit?assetId=" + assetId);
            return; 
        }
//        
        session.setAttribute("flash_success", "Delete tài sản thành công");
        response.sendRedirect(request.getContextPath() + "/admin/assets/read");
        
//        out.println("Post");
    }
    
}
