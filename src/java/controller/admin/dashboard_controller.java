package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.dao.AssetDAO;
import model.dao.ClassroomDAO;

public class dashboard_controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        AssetDAO assetDAO = new AssetDAO();
        ClassroomDAO classroomDAO = new ClassroomDAO();

        // Thống kê tài sản
        int totalAssets = assetDAO.countTotalAssets();
        int borrowedAssets = assetDAO.countByStatusName("Mượn");
        int brokenAssets = assetDAO.countBrokenAndMaintenance();

        // Thống kê phòng học
        int totalClassrooms = classroomDAO.getTotalClassrooms();

        // Set attribute sang JSP
        request.setAttribute("totalAssets", totalAssets);
        request.setAttribute("borrowedAssets", borrowedAssets);
        request.setAttribute("brokenAssets", brokenAssets);
        request.setAttribute("totalClassrooms", totalClassrooms);

        request.getRequestDispatcher("/WEB-INF/admin/dashboard.jsp")
               .forward(request, response);
    }
}