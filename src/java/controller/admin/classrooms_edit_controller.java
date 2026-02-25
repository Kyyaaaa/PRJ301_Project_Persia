/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Classroom;
import model.dao.ClassroomDAO;
import utilities.Validate;

/**
 *
 * @author ADMIN
 */
public class classrooms_edit_controller extends HttpServlet {
    
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
        
        String classroomId = request.getParameter("classroomId");
//        out.println(classroomId);
        
        try {
            if(new ClassroomDAO().isExist(classroomId)) {
                request.setAttribute("classroomToEdit", classroomId); // Đặt đối tượng user vào request
                request.getRequestDispatcher("/WEB-INF/admin/classrooms_edit.jsp").forward(request, response);
            } 
            else {
                 // Xử lý khi không tìm thấy asset
                response.sendRedirect(request.getContextPath() + "/admin/classrooms/read");
            }
        }
        catch(Exception e) { // Bắt các lỗi khác có thể xảy ra trong DAO
           session.setAttribute("flash_error", "An error occurred while fetching user data: " + e.getMessage());
           response.sendRedirect(request.getContextPath() + "/admin/classrooms/read");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        // 1. Lấy dữ liệu từ form
        String classroom_name = request.getParameter("classroom_name");
        String location = request.getParameter("location");
        
        String classroomId = request.getParameter("classroomToEdit");
        HttpSession session = request.getSession();
        
        // 2. Validate
        if (!Validate.validateClassroomName(classroom_name)) {
            session.setAttribute("flash_error", "Invalid room name");
            response.sendRedirect(request.getContextPath() + "/admin/classrooms/create");
            return; 
        }
        if (!Validate.validateLocation(location)) {
            session.setAttribute("flash_error", "Invalid location");
            response.sendRedirect(request.getContextPath() + "/admin/classrooms/create");
            return; 
        }
        
        // 3. Cập nhật
        Classroom classroom = new ClassroomDAO().update(Integer.parseInt(classroomId), classroom_name.trim(), location.trim());
        
        if (classroom == null) {
            session.setAttribute("flash_error", "Failed to update classroom");
            response.sendRedirect(request.getContextPath() + "/admin/classrooms/create");
            return; 
        }
        
        // 4. Hiện thông báo và quay về
        session.setAttribute("flash_success", "Classroom updated successfully");
        response.sendRedirect(request.getContextPath() + "/admin/classrooms/read");
    }
    
}
