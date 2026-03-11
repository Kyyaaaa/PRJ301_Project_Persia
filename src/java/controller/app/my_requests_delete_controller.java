package controller.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.AssetRequest;
import model.User;
import model.dao.AssetRequestDAO;

public class my_requests_delete_controller extends HttpServlet {
    
    private AssetRequestDAO assetRequestDAO = new AssetRequestDAO();

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
        
        String requestIdStr = request.getParameter("requestId");
        if (requestIdStr != null && !requestIdStr.isEmpty()) {
            try {
                int requestId = Integer.parseInt(requestIdStr);
                AssetRequest req = assetRequestDAO.findById(requestId);
                
                if (req != null) {
                    session = request.getSession();
                    User user = (User) session.getAttribute("user");
                    if (user != null && req.getRequestedBy().equals(user.getUsername()) && "PENDING".equals(req.getStatus())) {
                        request.setAttribute("requestId", requestIdStr);
                        request.getRequestDispatcher("/WEB-INF/app/my-requests_delete.jsp").forward(request, response);
                        return;
                    } else {
                        session.setAttribute("flash_error", "You can only delete your own pending requests.");
                        response.sendRedirect(request.getContextPath() + "/app/my-requests");
                        return;
                    }
                } else {
                    session = request.getSession();
                    session.setAttribute("flash_error", "Request not found.");
                    response.sendRedirect(request.getContextPath() + "/app/my-requests");
                    return;
                }
            } catch (NumberFormatException e) {
                session = request.getSession();
                session.setAttribute("flash_error", "Invalid request ID format.");
                response.sendRedirect(request.getContextPath() + "/app/my-requests");
                return;
            }
        } else {
            session = request.getSession();
            session.setAttribute("flash_error", "Request ID is required.");
        }
        
        response.sendRedirect(request.getContextPath() + "/app/my-requests");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        
        String requestIdStr = request.getParameter("requestId");
        HttpSession session = request.getSession();
        
        if (requestIdStr != null && !requestIdStr.isEmpty()) {
            try {
                int requestId = Integer.parseInt(requestIdStr);
                AssetRequest req = assetRequestDAO.findById(requestId);
                
                if (req != null) {
                    User user = (User) session.getAttribute("user");
                    if (user != null && req.getRequestedBy().equals(user.getUsername()) && "PENDING".equals(req.getStatus())) {
                        if (assetRequestDAO.delete(requestIdStr)) {
                            session.setAttribute("flash_success", "Request deleted successfully.");
                        } else {
                            session.setAttribute("flash_error", "Failed to delete the request.");
                        }
                    } else {
                        session.setAttribute("flash_error", "You can only delete your own pending requests.");
                    }
                } else {
                    session.setAttribute("flash_error", "Request not found.");
                }
            } catch (NumberFormatException e) {
                session.setAttribute("flash_error", "Invalid request ID format.");
            } catch (Exception e) {
                session.setAttribute("flash_error", "An error occurred: " + e.getMessage());
            }
        } else {
            session.setAttribute("flash_error", "Request ID is missing.");
        }
        
        response.sendRedirect(request.getContextPath() + "/app/my-requests");
    }
}
