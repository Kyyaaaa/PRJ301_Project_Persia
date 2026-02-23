<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Role" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create a new user</h1>
        <form action="<%= request.getContextPath() %>/admin/users/create" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td>Role:</td>
                    <td>
                        <select name="role_id">
                        <%
                            List<Role> list_role = (ArrayList<Role>)request.getAttribute("list_role");
                            if(list_role != null) {
                                for(Role i : list_role) {
                        %>
                                    <option value="<%= i.role_id %>"><%= i.role_name %></option>
                        <%
                                }
                            }
                        %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">Create</button>
                    </td>
                </tr>
            </table>
        </form>
            
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p style="color:red;"><%= error %></p>
        <%
            }
        %>
    </body>
</html>
