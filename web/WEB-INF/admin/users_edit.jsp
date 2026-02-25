<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        <h1>
            Edit a user
        </h1>
        <form action="<%= request.getContextPath() %>/admin/users/edit" method="post">
            <table>
                <tr>
                    <td>New password:</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td>New role:</td>
                    <td>
                        <select name="role_id">
                            <option value="1">admin</option>
                            <option value="2">teacher</option>
                            <option value="3">student</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">Update</button>
                    </td>
                </tr>
            </table>
            
            <input type="hidden" name="userToEdit" value="<%= (String) request.getAttribute("userToEdit")%>">
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
