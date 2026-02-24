<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AssetStatus" %>
<%@page import="model.AssetType" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        <h1>Edit asset</h1>
        <form action="<%= request.getContextPath() %>/admin/assets/edit" method="post">
            <table>
                <tr>
                    <td>Asset name:</td>
                    <td><input type="text" name="asset_name" required></td>
                </tr>
                <tr>
                    <td>Type:</td>
                    <td>
                        <select name="type_id">
                        <%
                            List<AssetType> list_type = (ArrayList<AssetType>)request.getAttribute("list_type");
                            if(list_type != null) {
                                for(AssetType i : list_type) {
                        %>
                                    <option value="<%= i.typeId %>"><%= i.typeName %></option>
                        <%
                                }
                            }
                        %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Status:</td>
                    <td>
                        <select name="status_id">
                        <%
                            List<AssetStatus> list_status = (ArrayList<AssetStatus>)request.getAttribute("list_status");
                            if(list_status != null) {
                                for(AssetStatus i : list_status) {
                        %>
                                    <option value="<%= i.statusId %>"><%= i.statusName %></option>
                        <%
                                }
                            }
                        %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">Edit</button>
                    </td>
                </tr>
            </table>
                        
            <input type="hidden" name="assetToEdit" value="<%= (String) request.getAttribute("assetToEdit")%>">
        </form>
            
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
