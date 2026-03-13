<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AssetRequest" %>
<%@page import="java.util.List" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requests</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />

<div class="container container-fluid mt-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2>Asset Requests</h2>
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

            List<AssetRequest> listAssetRequest =
                (List<AssetRequest>) request.getAttribute("listAssetRequest");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        %>

        <div class="table-wrapper table-responsive">
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Asset ID</th>
                        <th>Classroom</th>
                        <th>Requested By</th>
                        <th>Purpose</th>
                        <th>Request Date</th>
                        <th>Expected Return</th>
                        <th>Status</th>
                        <th>Reviewed By</th>
                        <th>Reviewed Date</th>
                        <th>Review Note</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (listAssetRequest != null && !listAssetRequest.isEmpty()) {
                            for (AssetRequest r : listAssetRequest) {
                    %>
                    <tr>
                        <td><%= r.getRequestId() %></td>
                        <td><%= r.getAssetId() %></td>
                        <td><%= r.getClassroomId() %></td>
                        <td><%= r.getRequestedBy() %></td>
                        <td><%= r.getPurpose() != null ? r.getPurpose() : "" %></td>
                        <td>
                            <%= r.getRequestDate() != null
                                    ? sdf.format(r.getRequestDate())
                                    : "" %>
                        </td>
                        <td>
                            <%= r.getExpectedReturnDate() != null
                                    ? sdf.format(r.getExpectedReturnDate())
                                    : "" %>
                        </td>
                        <td>
                            <% if("PENDING".equals(r.getStatus())) { %>
                                <span class="badge bg-warning text-dark"><%= r.getStatus() %></span>
                            <% } else if("APPROVE".equals(r.getStatus())) { %>
                                <span class="badge bg-success"><%= r.getStatus() %></span>
                            <% } else { %>
                                <span class="badge bg-danger"><%= r.getStatus() %></span>
                            <% } %>
                        </td>
                        <td><%= r.getReviewedBy() != null ? r.getReviewedBy() : "" %></td>
                        <td>
                            <%= r.getReviewedDate() != null
                                    ? sdf.format(r.getReviewedDate())
                                    : "" %>
                        </td>
                        <td><%= r.getReviewNote() != null ? r.getReviewNote() : "" %></td>
                        <td class="text-center action-buttons">
                            <%
                                if(r.getStatus().equals("PENDING")) {
                            %>
                            <a href="<%= request.getContextPath() %>/admin/requests/edit?requestId=<%= r.getRequestId() %>" class="btn btn-primary btn-sm mb-1" title="Approve / Reject">
                                <i class="bi bi-pencil-square"></i>
                            </a>
                            <%
                                }
                            %>
                            <a href="<%= request.getContextPath() %>/admin/requests/delete?requestId=<%= r.getRequestId() %>" class="btn btn-danger btn-sm mb-1" title="Delete">
                                <i class="bi bi-trash"></i>
                            </a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="12" class="text-center text-muted py-4">
                            <i class="bi bi-inbox fs-2 d-block mb-2"></i>
                            No asset requests found.
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