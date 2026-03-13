<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Asset" %>
<%@page import="model.View.AssetView" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asset Inventory</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2><i class="bi bi-box-seam me-2"></i>Asset Inventory</h2>
                <a href="<%= request.getContextPath() %>/admin/assets/create" class="btn btn-primary">
                    <i class="bi bi-plus-circle me-1"></i> Create a new asset
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
                
            <div class="table-wrapper table-responsive shadow-sm">
                <table class="table table-bordered table-hover align-middle mb-0">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Asset Name</th>
                            <th>Type</th>
                            <th>Category</th>
                            <th>Status</th>
                            <th class="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<AssetView> list = (ArrayList<AssetView>)request.getAttribute("list");
                            if(list != null && !list.isEmpty()) {
                                for(AssetView i : list) {
                        %>
                            <tr>
                                <td class="fw-bold text-muted">#<%= i.assetId %></td>
                                <td class="fw-medium text-dark"><i class="bi bi-box me-2 text-secondary"></i><%= i.assetName %></td>
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
                                <td class="text-center action-buttons">
                                    <a href="<%= request.getContextPath() %>/admin/assets/edit?assetId=<%= i.assetId %>" class="btn btn-primary btn-sm mx-1" title="Edit Asset">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>
                                    <a href="<%= request.getContextPath() %>/admin/assets/delete?assetId=<%= i.assetId %>" class="btn btn-danger btn-sm mx-1" title="Delete Asset">
                                        <i class="bi bi-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="6" class="text-center text-muted py-5">
                                    <i class="bi bi-inbox fs-1 d-block mb-3 text-secondary"></i>
                                    <h5 class="fw-light">No assets found in the inventory</h5>
                                </td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
