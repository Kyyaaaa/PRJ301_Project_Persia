<%-- 
    Document   : users
    Created on : Feb 9, 2026, 8:54:44 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="<%= request.getContextPath() %>/admin/users/create">
            <button>Create New User</button>
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
                    if(users != null) {
                        for(User user : users) {
                %>
                    <tr>
                        <td><%= user.username %></td>
                        <td><%= user.password %></td>
                        <td><%= user.role_id %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/users/edit?username=<%= user.username %>">
                                <button>Update</button>
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
