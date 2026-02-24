package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Classroom;
import model.dao.ClassroomDAO;
import utilities.Validate;

public class classrooms_create_controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            String error = (String) session.getAttribute("flash_error");
            if (error != null) {
                request.setAttribute("error", error);
                session.removeAttribute("flash_error");
            }
        }
        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/classrooms_create.jsp")
               .forward(request, response);
        
//        out.println("DoGet");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        // 1. Lấy dữ liệu từ form
        String classroom_name = request.getParameter("classroom_name");
        String location = request.getParameter("location");
        
        HttpSession session = request.getSession();
        
        // 2. Validate
        if (!Validate.validateClassroomName(classroom_name)) {
            session.setAttribute("flash_error", "Tên lớp học không hợp lệ");
            response.sendRedirect(request.getContextPath() + "/admin/classrooms/create");
            return; 
        }
        if (!Validate.validateLocation(location)) {
            session.setAttribute("flash_error", "Vị trí không hợp lệ");
            response.sendRedirect(request.getContextPath() + "/admin/classrooms/create");
            return; 
        }
        
        // 3. Tạo tài sản mới
        Classroom classroom = new ClassroomDAO().create(classroom_name.trim(), location.trim());
        
        if (classroom == null) {
            session.setAttribute("flash_error", "Tạo lớp học thất bại");
            response.sendRedirect(request.getContextPath() + "/admin/classrooms/create");
            return; 
        }
        
        // 4. Hiện thông báo và quay về
        session.setAttribute("flash_success", "Tạo lớp học thành công");
        response.sendRedirect(request.getContextPath() + "/admin/classrooms/read");
    }
}
