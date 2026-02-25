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
        
        List<User> users = new UserDAO().getAllUsers();
        request.setAttribute("users", users);
        
        List<Role> list_role = new RoleDAO().getAllRoles();
        request.setAttribute("list_role", list_role);
        
        request.setAttribute("size", users.size());
        StringBuffer lol = new StringBuffer("");
        for(User i : users) lol.append(i.username);
        request.setAttribute("lol", lol);

        
        // Forward the request to the JSP view to render the dashboard page
        request.getRequestDispatcher("/WEB-INF/admin/users.jsp")
               .forward(request, response);
    }
}
