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
import model.Classroom;
import model.User;
import model.View.AssetView;
import model.dao.AssetAssignmentDAO;
import model.dao.AssetDAO;
import model.dao.AssetRequestDAO;
import model.dao.ClassroomDAO;

public class assets_request_controller extends HttpServlet {
    
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
        
        List<AssetView> allAssets = new AssetDAO().getAllAssetViews();
        List<AssetView> listAssetView = new java.util.ArrayList<>();
        if (allAssets != null) {
            for (AssetView av : allAssets) {
                if ("OK".equalsIgnoreCase(av.getStatusName())) {
                    listAssetView.add(av);
                }
            }
        }
        request.setAttribute("listAssetView", listAssetView);
        
        List<Classroom> listClassroom = new ClassroomDAO().getAllClassrooms();
        request.setAttribute("listClassroom", listClassroom);
        
        request.getRequestDispatcher("/WEB-INF/app/assets_request.jsp")
               .forward(request, response);
        
//        out.println("Get");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();

        String assetIdRaw = request.getParameter("assetId");
        String classroomIdRaw = request.getParameter("classroomId");
        String purpose = request.getParameter("purpose");
        String expectedReturnDateRaw = request.getParameter("expectedReturnDate");

        // ===== Validate assetId =====
        int assetId;
        try {
            assetId = Integer.parseInt(assetIdRaw);
            if (assetId <= 0) {
                throw new NumberFormatException();
            }
        } catch (Exception e) {
            session.setAttribute("flash_error", "Invalid asset selected");
            response.sendRedirect(request.getContextPath() + "/app/assets/request");
            return;
        }

        // ===== Validate classroomId =====
        int classroomId;
        try {
            classroomId = Integer.parseInt(classroomIdRaw);
            if (classroomId <= 0) {
                throw new NumberFormatException();
            }
        } catch (Exception e) {
            session.setAttribute("flash_error", "Invalid classroom selected");
            response.sendRedirect(request.getContextPath() + "/app/assets/request");
            return;
        }

        // ===== Validate purpose =====
        if (purpose != null) {
            purpose = purpose.trim();
            if (purpose.length() > 255) {
                session.setAttribute("flash_error", "Purpose must not exceed 255 characters");
                response.sendRedirect(request.getContextPath() + "/app/assets/request");
                return;
            }
            if (purpose.isEmpty()) {
                purpose = null;
            }
        }

        // ===== Validate expected return date =====
        java.sql.Date expectedReturnDate = null;
        if (expectedReturnDateRaw != null && !expectedReturnDateRaw.isEmpty()) {
            try {
                expectedReturnDate = java.sql.Date.valueOf(expectedReturnDateRaw);

                java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
                if (expectedReturnDate.before(today)) {
                    session.setAttribute("flash_error", "Expected return date cannot be earlier than today");
                    response.sendRedirect(request.getContextPath() + "/app/assets/request");
                    return;
                }
            } catch (Exception e) {
                session.setAttribute("flash_error", "Invalid expected return date format");
                response.sendRedirect(request.getContextPath() + "/app/assets/request");
                return;
            }
        }

        // ===== Check login =====
        User username = (User) session.getAttribute("user");
        if (username == null) {
            session.setAttribute("flash_error", "You must be logged in to submit a request");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // ===== Check if asset is already assigned =====
        if (new AssetAssignmentDAO().isAssetCurrentlyAssigned(assetId)) {
            session.setAttribute("flash_error", "Tài sản này đã có người mượn, không thể tạo request");
            response.sendRedirect(request.getContextPath() + "/app/assets/request");
            return;
        }

        // ===== TODO: Call DAO to create request =====
        AssetRequest req = new AssetRequest();
        req.setAssetId(assetId);
        req.setClassroomId(classroomId);
        req.setRequestedBy(username.username);
        req.setPurpose(purpose);
        req.setExpectedReturnDate(expectedReturnDate);
        
        new AssetRequestDAO().create(req);

//        out.println(username.username);

        session.setAttribute("flash_success", "Asset request submitted successfully");
        response.sendRedirect(request.getContextPath() + "/app/assets/request");
    }
}
