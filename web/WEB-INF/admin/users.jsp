
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link rel="stylesheet" href="/PRJ301_Project_Persia/static/css/styles.css" />
    </head>
    <body>
        <!-- header -->
        <jsp:include page="/WEB-INF/layout/header.jsp"/>
        
         <!-- sidebar -->
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp"/>
        <div id="main" class="main-content">   
            
            <div class="dashboard-container">
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
                            <th>RoleID</th>
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
            </div>
        </div>
    </body>
</html>
