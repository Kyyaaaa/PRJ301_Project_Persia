package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Asset;
import model.AssetStatus;
import model.AssetType;
import model.dao.AssetTypeDAO;
import model.Role;
import model.dao.RoleDAO;
import model.User;
import model.View.AssetView;
import model.dao.AssetDAO;
import model.dao.AssetStatusDAO;
import model.dao.UserDAO;
import utilities.Validate;

public class assets_create_controller extends HttpServlet {
    
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
        
        // Statuses list
        List<AssetStatus> list_status = new AssetStatusDAO().getAllAssetStatuses();
        request.setAttribute("list_status", list_status);
        
        // Types list
        List<AssetType> list_type = new AssetTypeDAO().getAllAssetTypes();
        request.setAttribute("list_type", list_type);
        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/assets_create.jsp")
               .forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        // 1. Lấy dữ liệu từ form
        String asset_name = request.getParameter("asset_name");
        String type_id = request.getParameter("type_id");
        String status_id = request.getParameter("status_id");
        
        HttpSession session = request.getSession();
        
        // 2. Validate
        if (!Validate.validateAssetName(asset_name)) {
            session.setAttribute("flash_error", "Asset name must not be empty and must not exceed 255 characters");
            response.sendRedirect(request.getContextPath() + "/admin/assets/create");
            return; 
        }
        if (!Validate.validateTypeId(type_id)) {
            session.setAttribute("flash_error", "Invalid asset type");
            response.sendRedirect(request.getContextPath() + "/admin/assets/create");
            return; 
        }
        if (!Validate.validateStatusId(status_id)) {
            session.setAttribute("flash_error", "Invalid asset status");
            response.sendRedirect(request.getContextPath() + "/admin/assets/create");
            return; 
        }
        
        // 3. Tạo tài sản mới
        Asset asset = new AssetDAO().create(asset_name.trim(), Integer.parseInt(type_id), Integer.parseInt(status_id));

        if (asset == null) {
            session.setAttribute("flash_error", "Failed to create asset");
            response.sendRedirect(request.getContextPath() + "/admin/assets/create");
            return; 
        }
        
        // 4. Hiện thông báo và quay về
        session.setAttribute("flash_success", "Asset created successfully");
        response.sendRedirect(request.getContextPath() + "/admin/assets/read");
        
//        out.println(asset_name + " " + type_id + " " + status_id);
    }
}
