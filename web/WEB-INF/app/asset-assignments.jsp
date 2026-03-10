<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AssetAssignment" %>
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
            
        <table border="1">
            <thead>
                <tr>
                    <th>Assignment ID</th>
                    <th>Asset ID</th>
                    <th>Classroom ID</th>
                    <th>Assigned Date</th>
                    <th>Return Date</th>
                    <th>Assigned By</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<AssetAssignment> list = (ArrayList<AssetAssignment>)request.getAttribute("list");
                    if(list != null) {
                        for(AssetAssignment i : list) {
                %>
                <tr>
                    <td><%= i.assignmentId %></td>
                    <td><%= i.assetId %></td>
                    <td><%= i.classroomId %></td>
                    <td><%= i.assignedDate %></td>
                    <td><%= i.returnDate %></td>
                    <td><%= i.assignedBy %></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
