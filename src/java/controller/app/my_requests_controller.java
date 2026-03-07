package controller.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.AssetRequest;
import model.User;
import model.dao.AssetRequestDAO;

public class my_requests_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        List<AssetRequest> listAssetRequest = new AssetRequestDAO().getAssetRequestsByUsername(user.username);
        request.setAttribute("listAssetRequest", listAssetRequest);

        request.getRequestDispatcher("/WEB-INF/app/my_requests.jsp")
               .forward(request, response);
        
//        out.println("Get");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("Post");
    }
}
