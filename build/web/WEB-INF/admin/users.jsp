<%-- 
    Document   : users
    Created on : Feb 9, 2026, 8:54:44 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="model.Role" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        <a href="<%= request.getContextPath() %>/admin/users/create">
            <button>Create a new user</button>
        </a>
        
        <%
            String success = (String) request.getAttribute("success");
            if (success != null) {
        %>
            <p style="color:green;"><%= success %></p>
        <%
            }
        %>
        
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p style="color:red;"><%= error %></p>
        <%
            }
        %>
            
        <table>
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<User> users = (ArrayList<User>)request.getAttribute("users");
                    List<Role> list_role = (ArrayList<Role>)request.getAttribute("list_role");
                    
                    Map<Integer, String> roleMap = new HashMap<>();
                    if (list_role != null) {
                        for (Role role : list_role) {
                            roleMap.put(role.role_id, role.role_name);
                        }
                    }
                    
                    if(users != null) {
                        for(User user : users) {
                %>
                    <tr>
                        <td><%= user.username %></td>
                        <td><%= user.password %></td>
                        <td><%= roleMap.get(user.role_id) %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/users/edit?username=<%= user.username %>">
                                <button>Edit</button>
                            </a>
                        </td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/users/delete?username=<%= user.username %>">
                                <button>Delete</button>
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
