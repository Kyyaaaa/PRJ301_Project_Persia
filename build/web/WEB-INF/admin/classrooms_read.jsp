<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Asset" %>
<%@page import="model.Classroom" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        <a href="<%= request.getContextPath() %>/admin/classrooms/create">
            <button>Create a new classroom</button>
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
                    <th>ID</th>
                    <th>Room name</th>
                    <th>Location</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Classroom> list = (ArrayList<Classroom>)request.getAttribute("list");
                    if(list != null) {
                        for(Classroom i : list) {
                %>
                    <tr>
                        <td><%= i.classroomId %></td>
                        <td><%= i.classroomName %></td>
                        <td><%= i.location %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/classrooms/edit?classroomId=<%= i.classroomId %>">
                                <button>Edit</button>
                            </a>
                        </td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/classrooms/delete?classroomId=<%= i.classroomId %>">
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
