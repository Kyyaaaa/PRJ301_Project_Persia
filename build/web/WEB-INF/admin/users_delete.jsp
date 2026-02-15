<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            Bạn có chắc chắn muốn xóa user này không?
        </h1>
        <form action="<%= request.getContextPath() %>/admin/users/delete" method="post">
            <button type="submit">Confirm</button>
            
            <input type="hidden" name="userToEdit" value="<%= (String) request.getAttribute("userToEdit")%>">
        </form>

    </body>
</html>
