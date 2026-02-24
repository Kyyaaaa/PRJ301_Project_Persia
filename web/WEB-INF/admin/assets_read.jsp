<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Asset" %>
<%@page import="model.View.AssetView" %>
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
        <a href="<%= request.getContextPath() %>/admin/assets/create">
            <button>Create New Asset</button>
        </a>
        
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
            
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Category</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<AssetView> list = (ArrayList<AssetView>)request.getAttribute("list");
                    if(list != null) {
                        for(AssetView i : list) {
                %>
                    <tr>
                        <td><%= i.assetId %></td>
                        <td><%= i.assetName %></td>
                        <td><%= i.typeName %></td>
                        <td><%= i.categoryName %></td>
                        <td><%= i.statusName %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/assets/edit?assetId=<%= i.assetId %>">
                                <button>Edit</button>
                            </a>
                        </td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/assets/delete?assetId=<%= i.assetId %>">
                                <button>Delete</button>
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
