<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Asset Assignment</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
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
        <form action="<%= request.getContextPath() %>/admin/asset-assignments/delete" method="post">
            <button type="submit">Confirm</button>
            <a href="<%= request.getContextPath() %>/admin/asset-assignments">Cancel</a>
            
            <input type="hidden" name="assetAssignmentToEdit" value="<%= (String) request.getAttribute("assetAssignmentToEdit")%>">
        </form>
    </body>
</html>
