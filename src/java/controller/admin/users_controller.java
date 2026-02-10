package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Role;
import model.dao.RoleDAO;
import model.User;
import model.dao.UserDAO;

public class users_controller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter out = response.getWriter();
        
        // Lấy session
        HttpSession session = request.getSession(false);
        
        // Nếu chưa login
        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/login/login.jsp")
                   .forward(request, response);
            return;
        }
        
        User user = (User)session.getAttribute("user");
        RoleDAO role_dao = new RoleDAO();
        
        // Nếu không phải admin thì không cho vào
        Role role = role_dao.getRoleById(user.role_id);
        if(!role.role_name.equals("admin")) {
            out.println("Bạn không có quyền truy cập vào đây");
            return;
        }
        
        List<User> users = new UserDAO().getAllUsers();
        request.setAttribute("users", users);
        
        request.setAttribute("size", users.size());
        StringBuffer lol = new StringBuffer("");
        for(User i : users) lol.append(i.username);
        request.setAttribute("lol", lol);

        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/users.jsp")
               .forward(request, response);
    }
}
