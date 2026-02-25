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
            Are you sure you want to delete this classroom?
        </h1>
        <form action="<%= request.getContextPath() %>/admin/classrooms/delete" method="post">
            <button type="submit">Confirm</button>
            
            <input type="hidden" name="classroomToEdit" value="<%= (String) request.getAttribute("classroomToEdit")%>">
        </form>

    </body>
</html>
