package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Asset;
import model.AssetStatus;
import model.AssetType;
import model.DBContext;
import model.dao.AssetDAO;
import model.dao.AssetStatusDAO;
import model.dao.AssetTypeDAO;
import model.dao.RoleDAO;
import utilities.Validate;

public class assets_edit_controller extends HttpServlet {
    
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
        
        String assetId = request.getParameter("assetId");
//        out.println(assetId);
        
        try {
            if(new AssetDAO().isExist(assetId)) {
                if (new model.dao.AssetAssignmentDAO().isAssetCurrentlyAssigned(Integer.parseInt(assetId))) {
                    session = request.getSession();
                    session.setAttribute("flash_error", "This asset is currently assigned and cannot be edited.");
                    response.sendRedirect(request.getContextPath() + "/admin/assets/read");
                    return;
                }

                Asset currentAsset = new AssetDAO().findById(assetId);
                request.setAttribute("currentAsset", currentAsset);
                request.setAttribute("assetToEdit", assetId); // Đặt đối tượng user vào request
                request.getRequestDispatcher("/WEB-INF/admin/assets_edit.jsp").forward(request, response);
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
        
        String assetId = request.getParameter("assetToEdit");

        HttpSession session = request.getSession();
        
        // 2. Validate
        if (!Validate.validateAssetName(asset_name)) {
            session.setAttribute("flash_error", "Asset name must not be empty and must not exceed 255 characters");
            response.sendRedirect(request.getContextPath() + "/admin/assets/edit?assetId=" + assetId);
            return; 
        }
        if (!Validate.validateTypeId(type_id)) {
            session.setAttribute("flash_error", "Invalid asset type");
            response.sendRedirect(request.getContextPath() + "/admin/assets/edit?assetId=" + assetId);
            return; 
        }
        if (!Validate.validateStatusId(status_id)) {
            session.setAttribute("flash_error", "Invalid asset status");
            response.sendRedirect(request.getContextPath() + "/admin/assets/edit?assetId=" + assetId);
            return; 
        }
        if(!new AssetDAO().isExist(assetId)) {
            session.setAttribute("flash_error", "Asset does not exist");
            response.sendRedirect(request.getContextPath() + "/admin/assets/edit?assetId=" + assetId);
            return;
        }
        
        if (new model.dao.AssetAssignmentDAO().isAssetCurrentlyAssigned(Integer.parseInt(assetId))) {
            session.setAttribute("flash_error", "This asset is currently assigned and cannot be edited.");
            response.sendRedirect(request.getContextPath() + "/admin/assets/read");
            return;
        }
        
        // 3. Cập nhật tài sản
        Asset asset = new AssetDAO().update(Integer.parseInt(assetId), asset_name.trim(), Integer.parseInt(type_id), Integer.parseInt(status_id));
        
        // 4. Hiện thông báo và quay về
        session.setAttribute("flash_success", "Asset updated successfully");
        response.sendRedirect(request.getContextPath() + "/admin/assets/read");
        
//        out.println(asset_name + " " + type_id + " " + status_id + " " + assetId);
    }
}
