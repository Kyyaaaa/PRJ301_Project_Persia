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
            Are you sure you want to delete this asset?
        </h1>
        <form action="<%= request.getContextPath() %>/admin/assets/delete" method="post">
            <button type="submit">Confirm</button>
            
            <input type="hidden" name="assetToEdit" value="<%= (String) request.getAttribute("assetToEdit")%>">
        </form>

    </body>
</html>
