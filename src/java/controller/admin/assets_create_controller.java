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

public class assets_create_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
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
        
        String status_id = request.getParameter("status_id");
        
        out.println(status_id);
    }
}
