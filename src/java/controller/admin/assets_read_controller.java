package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Asset;
import model.AssetStatus;
import model.dao.AssetStatusDAO;
import model.Role;
import model.dao.RoleDAO;
import model.User;
import model.View.AssetView;
import model.dao.AssetDAO;
import model.dao.UserDAO;

public class assets_read_controller extends HttpServlet {
    
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
        
        List<AssetView> list = new AssetDAO().getAllAssetViews();
        request.setAttribute("list", list);
         
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/assets_read.jsp")
               .forward(request, response);

        // TEST
//        out.println("NGU");
//        List<AssetStatus> lol = new AssetStatusDAO().getAllAssetStatuses();
//        out.println(lol.size());
//        for(AssetStatus i : lol) {
//            out.println(i.statusId + " " + i.statusName);
//            out.println("<br>");
//        }
    }
}
