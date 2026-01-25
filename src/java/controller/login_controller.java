package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class login_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/login/login.jsp")
               .forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Kiểm tra rỗng
        if (username == null || password == null ||
            username.isEmpty() || password.isEmpty()) {

            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            request.getRequestDispatcher("/WEB-INF/login/login.jsp")
                   .forward(request, response);
            return;
        }

        // 3. Kiểm tra tài khoản (demo)
        if ("admin".equals(username) && "123".equals(password)) {

            // 4. Tạo session
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // 5. Redirect sang dashboard (Servlet)
            response.sendRedirect(
                request.getContextPath() + "/admin/dashboard"
            );

        } else {
            // 6. Sai tài khoản
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("/WEB-INF/login/login.jsp")
                   .forward(request, response);
        }
    }
}
