<%-- 
    Document   : login
    Created on : Jan 25, 2026, 1:36:24 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<!--        <h1>/login</h1>-->
        <form action="<%= request.getContextPath() %>/login" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">Login</button>
                    </td>
                </tr>
            </table>
        </form>
        <p style="color:red">${error}</p>
    </body>
</html>
