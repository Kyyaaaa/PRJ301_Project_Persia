package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import model.Role;
import model.User;
import model.dao.RoleDAO;

@WebFilter("/admin/*") // áp dụng cho tất cả /admin/...
public class admin_filter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {
        res.setContentType("text/html; charset = UTF-8");
        PrintWriter out = res.getWriter();
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(false);
        User user = (session != null)
                ? (User) session.getAttribute("user")
                : null;
        
        // Chưa đăng nhập
        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/login/login.jsp")
                   .forward(request, response);
            return;
        }
        
        // Không phải admin
        RoleDAO role_dao = new RoleDAO();
        
        // Nếu không phải admin thì không cho vào
        Role role = role_dao.getRoleById(user.role_id);
        if(!role.role_name.equals("admin")) {
            out.println("Bạn không có quyền truy cập vào đây");
            return;
        }
        
        // Đủ điều kiện thì cho đi tiếp
        chain.doFilter(req, res);
    }
}

