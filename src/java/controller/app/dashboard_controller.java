package controller.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.User;
import model.dao.AssetRequestDAO;
import model.dao.AssetAssignmentDAO;
import model.AssetRequest;

public class dashboard_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user != null) {
            String username = user.getUsername();
            AssetRequestDAO assetRequestDAO = new AssetRequestDAO();
            AssetAssignmentDAO assetAssignmentDAO = new AssetAssignmentDAO();
            
            List<AssetRequest> myRequests = assetRequestDAO.getAssetRequestsByUsername(username);
            int totalMyRequests = myRequests != null ? myRequests.size() : 0;
            
            int pendingMyRequests = 0;
            int approvedMyRequests = 0;
            if (myRequests != null) {
                for (AssetRequest req : myRequests) {
                    if ("PENDING".equalsIgnoreCase(req.getStatus())) {
                        pendingMyRequests++;
                    } else if ("APPROVED".equalsIgnoreCase(req.getStatus())) {
                        approvedMyRequests++;
                    }
                }
            }
            
            int totalMyAssignments = assetAssignmentDAO.getAllFromUser(username) != null ? assetAssignmentDAO.getAllFromUser(username).size() : 0;
            
            request.setAttribute("totalMyRequests", totalMyRequests);
            request.setAttribute("pendingMyRequests", pendingMyRequests);
            request.setAttribute("approvedMyRequests", approvedMyRequests);
            request.setAttribute("totalMyAssignments", totalMyAssignments);
            
            if (myRequests != null && myRequests.size() > 5) {
                request.setAttribute("recentMyRequests", myRequests.subList(0, 5));
            } else {
                request.setAttribute("recentMyRequests", myRequests);
            }
        }
        
        request.getRequestDispatcher("/WEB-INF/app/dashboard.jsp")
               .forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        doGet(request, response);
    }
}
