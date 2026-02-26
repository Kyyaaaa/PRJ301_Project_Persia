<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.View.AssetView" %>
<%@page import="model.Classroom" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Asset</title>
    </head>
    <body>

        <jsp:include page="/WEB-INF/layout/app_navbar.jsp" />

        <h1>Request an asset</h1>

        <%
            List<AssetView> listAssetView =
                (ArrayList<AssetView>) request.getAttribute("listAssetView");
            List<Classroom> listClassroom =
                (ArrayList<Classroom>) request.getAttribute("listClassroom");
        %>

        <form action="<%= request.getContextPath() %>/app/assets/request" method="post">
            <table>

                <!-- Asset -->
                <tr>
                    <td>Asset:</td>
                    <td>
                        <select name="assetId" required>
                            <option value="">-- Select asset --</option>
                            <%
                                for (AssetView a : listAssetView) {
                            %>
                                <option value="<%= a.getAssetId() %>">
                                    <%= a.getAssetName() %>
                                    ( <%= a.getTypeName() %> - <%= a.getStatusName() %> )
                                </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>

                <!-- Classroom -->
                <tr>
                    <td>Classroom:</td>
                    <td>
                        <select name="classroomId" required>
                            <option value="">-- Select classroom --</option>
                            <%
                                for (Classroom c : listClassroom) {
                            %>
                                <option value="<%= c.getClassroomId() %>">
                                    <%= c.getClassroomName() %>
                                    <%
                                        if (c.getLocation() != null) {
                                    %>
                                        - <%= c.getLocation() %>
                                    <%
                                        }
                                    %>
                                </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>

                <!-- Purpose -->
                <tr>
                    <td>Purpose:</td>
                    <td>
                        <textarea name="purpose" rows="4" cols="40"
                                  placeholder="Enter purpose of using asset"></textarea>
                    </td>
                </tr>

                <!-- Expected return date -->
                <tr>
                    <td>Expected return date:</td>
                    <td>
                        <input type="date" name="expectedReturnDate">
                    </td>
                </tr>

                <!-- Submit -->
                <tr>
                    <td colspan="2">
                        <button type="submit">Send request</button>
                    </td>
                </tr>

            </table>
        </form>

        <!-- Success message -->
        <%
            String success = (String) request.getAttribute("success");
            if (success != null) {
        %>
            <p style="color:green;"><%= success %></p>
        <%
            }
        %>

        <!-- Error message -->
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p style="color:red;"><%= error %></p>
        <%
            }
        %>

    </body>
</html>