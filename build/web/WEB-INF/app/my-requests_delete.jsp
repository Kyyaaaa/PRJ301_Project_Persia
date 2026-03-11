<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Request</title>
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
            Are you sure you want to delete this pending request?
        </h1>
        <form action="<%= request.getContextPath() %>/app/my-requests/delete" method="post">
            <button type="submit">Confirm</button>
            <a href="<%= request.getContextPath() %>/app/my-requests">
                <button type="button">Cancel</button>
            </a>
            
            <input type="hidden" name="requestId" value="<%= (String) request.getAttribute("requestId")%>">
        </form>
    </body>
</html>
