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
            
        <h1>
            Are you sure you want to delete this asset assignment?
        </h1>
        <form action="<%= request.getContextPath() %>/app/asset-assignments/delete" method="post">
            <button type="submit">Confirm</button>
            
            <input type="hidden" name="assetAssignmentToEdit" value="<%= (String) request.getAttribute("assetAssignmentToEdit")%>">
        </form>
    </body>
</html>
