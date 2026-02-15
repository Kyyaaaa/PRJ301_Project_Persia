<%-- 
    Document   : dashboard
    Created on : Jan 23, 2026, 1:58:22 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <%
            HttpSession sessionCurrent = request.getSession(false);

            if (sessionCurrent != null) {
                User user = (User) sessionCurrent.getAttribute("user");

                if (user != null) {
                    out.println("Username: " + user.username + "<br>");
                    out.println("Password: " + user.password + "<br>");
                    out.println("Role: " + user.role_id + "</br>");
                } else {
                    out.println("User chưa tồn tại trong session");
                }
            } else {
                out.println("Chưa đăng nhập");
            }
        %>
        
    </body>
</html>
