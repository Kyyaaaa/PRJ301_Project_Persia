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

public class requests_controller extends HttpServlet {
    
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
        
        List<AssetRequest> listAssetRequest = new AssetRequestDAO().getAllAssetRequests();
        request.setAttribute("listAssetRequest", listAssetRequest);
        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/requests.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
             
        out.println("post");
    }
}
