package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import model.Role;
import model.User;
import model.dao.RoleDAO;

@WebFilter("/app/*") // áp dụng cho tất cả /admin/...
public class app_filter implements Filter {

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
        
        // Đủ điều kiện thì cho đi tiếp
        chain.doFilter(req, res);
    }
}

