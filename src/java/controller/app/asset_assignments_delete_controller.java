package controller.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.dao.AssetAssignmentDAO;

public class asset_assignments_delete_controller extends HttpServlet {
    
    private AssetAssignmentDAO assetAssignmentDAO = new AssetAssignmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            String error = (String) session.getAttribute("flash_error");
            if (error != null) {
                request.setAttribute("error", error);
                session.removeAttribute("flash_error");
            }
        }
        
        String assignmentIdStr = request.getParameter("assignmentId");
        if (assignmentIdStr != null && !assignmentIdStr.isEmpty()) {
            try {
                int assignmentId = Integer.parseInt(assignmentIdStr);
                request.setAttribute("assetAssignmentToEdit", assignmentIdStr);
                request.getRequestDispatcher("/WEB-INF/app/asset-assignments_delete.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                session = request.getSession();
                session.setAttribute("flash_error", "Invalid assignment ID format.");
            }
        } else {
            session = request.getSession();
            session.setAttribute("flash_error", "Assignment ID is required.");
        }
        
        // Redirect back to the list if there's an error or missing ID
        response.sendRedirect(request.getContextPath() + "/app/asset-assignments");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        
        String assignmentIdStr = request.getParameter("assetAssignmentToEdit");
        HttpSession session = request.getSession();
        
        if (assignmentIdStr != null && !assignmentIdStr.isEmpty()) {
            try {
                int assignmentId = Integer.parseInt(assignmentIdStr);
                if (assetAssignmentDAO.delete(assignmentId)) {
                    session.setAttribute("flash_success", "Asset assignment deleted successfully.");
                } else {
                    session.setAttribute("flash_error", "Failed to delete the asset assignment.");
                }
            } catch (NumberFormatException e) {
                session.setAttribute("flash_error", "Invalid assignment ID format.");
            } catch (Exception e) {
                session.setAttribute("flash_error", "An error occurred: " + e.getMessage());
            }
        } else {
            session.setAttribute("flash_error", "Assignment ID is missing.");
        }
        
        response.sendRedirect(request.getContextPath() + "/app/asset-assignments");
    }
}
