<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Request</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />

        <h1>Edit Asset Request</h1>

        <form action="<%= request.getContextPath() %>/admin/requests/edit" method="post">
            <table>
                <tr>
                    <td>Action:</td>
                    <td>
                        <select name="status" required>
                            <option value="APPROVE">Approve</option>
                            <option value="REJECT">Reject</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Review Note:</td>
                    <td>
                        <input type="text" name="reviewNote" maxlength="255">
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit">Submit</button>
                    </td>
                </tr>
            </table>

            <!-- hidden request id -->
            <input type="hidden" name="requestToEdit"
                   value="<%= request.getAttribute("requestToEdit") %>">
        </form>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p style="color:red;"><%= error %></p>
        <%
            }
        %>

        <%
            String success = (String) request.getAttribute("success");
            if (success != null) {
        %>
            <p style="color:green;"><%= success %></p>
        <%
            }
        %>

    </body>
</html>