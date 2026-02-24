<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Classroom" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        <h1>Edit classroom</h1>
        <form action="<%= request.getContextPath() %>/admin/classrooms/edit" method="post">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="classroom_name" required></td>
                </tr>
                <tr>
                    <td>Location:</td>
                    <td><input type="text" name="location" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">Edit</button>
                    </td>
                </tr>
            </table>
            
            <input type="hidden" name="classroomToEdit" value="<%= (String) request.getAttribute("classroomToEdit")%>">
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
