<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Asset" %>
<%@page import="model.View.AssetView" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assets</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/app_navbar.jsp" />
        
        <div class="container container-fluid mt-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2>Available Assets</h2>
                <a href="<%= request.getContextPath() %>/app/assets/request" class="btn btn-primary">
                    <i class="bi bi-cart-plus"></i> Request to borrow asset
                </a>
            </div>
            
            <%
                String success = (String) request.getAttribute("success");
                if (success != null) {
            %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle me-1"></i> <%= success %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            <%
                }

                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-triangle me-1"></i> <%= error %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            <%
                }
            %>
                
            <div class="table-wrapper table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-light">
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
                            if(list != null && !list.isEmpty()) {
                                for(AssetView i : list) {
                        %>
                            <tr>
                                <td><%= i.assetId %></td>
                                <td><%= i.assetName %></td>
                                <td><span class="badge bg-secondary"><%= i.typeName %></span></td>
                                <td><%= i.categoryName %></td>
                                <td>
                                    <% if("OK".equalsIgnoreCase(i.statusName)) { %>
                                        <span class="badge bg-success"><i class="bi bi-check-circle"></i> <%= i.statusName %></span>
                                    <% } else if("BROKEN".equalsIgnoreCase(i.statusName) || "LOST".equalsIgnoreCase(i.statusName)) { %>
                                        <span class="badge bg-danger"><i class="bi bi-x-circle"></i> <%= i.statusName %></span>
                                    <% } else if("IN_USE".equalsIgnoreCase(i.statusName) || i.statusName != null && i.statusName.contains("MAINTENANCE")) { %>
                                        <span class="badge bg-warning text-dark"><i class="bi bi-exclamation-circle"></i> <%= i.statusName %></span>
                                    <% } else { %>
                                        <span class="badge bg-info text-dark"><%= i.statusName %></span>
                                    <% } %>
                                </td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="5" class="text-center text-muted py-4">
                                    <i class="bi bi-inbox fs-2 d-block mb-2"></i>
                                    No assets available.
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
