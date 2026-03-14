package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.dao.*;
import model.AssetRequest;

public class dashboard_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        
        UserDAO userDAO = new UserDAO();
        AssetDAO assetDAO = new AssetDAO();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        AssetRequestDAO assetRequestDAO = new AssetRequestDAO();
        AssetAssignmentDAO assetAssignmentDAO = new AssetAssignmentDAO();
        
        int totalUsers = userDAO.getAllUsers() != null ? userDAO.getAllUsers().size() : 0;
        int totalAssets = assetDAO.getAllAssets() != null ? assetDAO.getAllAssets().size() : 0;
        int totalClassrooms = classroomDAO.getAllClassrooms() != null ? classroomDAO.getAllClassrooms().size() : 0;
        List<AssetRequest> requests = assetRequestDAO.getAllAssetRequests();
        int totalRequests = requests != null ? requests.size() : 0;
        int totalAssignments = assetAssignmentDAO.getAllAssetAssignments() != null ? assetAssignmentDAO.getAllAssetAssignments().size() : 0;
        
        int pendingRequests = 0;
        if (requests != null) {
            for (AssetRequest req : requests) {
                if ("PENDING".equalsIgnoreCase(req.getStatus())) {
                    pendingRequests++;
                }
            }
        }
        
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalAssets", totalAssets);
        request.setAttribute("totalClassrooms", totalClassrooms);
        request.setAttribute("totalRequests", totalRequests);
        request.setAttribute("pendingRequests", pendingRequests);
        request.setAttribute("totalAssignments", totalAssignments);
        
        if (requests != null && requests.size() > 5) {
            request.setAttribute("recentRequests", requests.subList(0, 5));
        } else {
            request.setAttribute("recentRequests", requests);
        }
        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/dashboard.jsp")
               .forward(request, response);
    }
}
