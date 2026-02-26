<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <jsp:include page="/WEB-INF/layout/app_navbar.jsp" />
        
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
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
