<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AssetAssignment" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Asset Assignments</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2><i class="bi bi-link-45deg me-2 text-primary"></i>All Asset Assignments System</h2>
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
                            <th>Assign ID</th>
                            <th>Asset ID</th>
                            <th>Classroom ID</th>
                            <th>Assigned Date</th>
                            <th>Return Date</th>
                            <th>Assigned By</th>
                            <th class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<AssetAssignment> list = (ArrayList<AssetAssignment>)request.getAttribute("list");
                            if(list != null && !list.isEmpty()) {
                                for(AssetAssignment i : list) {
                        %>
                        <tr>
                            <td class="fw-bold text-muted">#<%= i.assignmentId %></td>
                            <td><span class="badge bg-primary rounded-pill"><i class="bi bi-box-seam me-1"></i><%= i.assetId %></span></td>
                            <td><i class="bi bi-door-open text-secondary me-1"></i><%= i.classroomId %></td>
                            <td><span class="badge bg-secondary"><i class="bi bi-calendar-check me-1"></i><%= i.assignedDate %></span></td>
                            <td>
                                <% if(i.returnDate != null) { %>
                                    <span class="badge bg-info text-dark"><i class="bi bi-calendar-event me-1"></i><%= i.returnDate %></span>
                                <% } else { %>
                                    <span class="text-muted fst-italic">Not specified</span>
                                <% } %>
                            </td>
                            <td class="fw-medium text-dark"><i class="bi bi-person-badge text-secondary me-1"></i><%= i.assignedBy %></td>
                            <td class="text-center action-buttons">
                                <a href="<%= request.getContextPath() %>/admin/asset-assignments/delete?assignmentId=<%= i.assignmentId %>" class="btn btn-outline-danger btn-sm" title="Revoke Assignment">
                                    <i class="bi bi-trash"></i> Remove
                                </a>
                            </td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center text-muted py-5">
                                <i class="bi bi-inbox fs-1 d-block mb-3 text-secondary"></i>
                                <h5 class="fw-light">No asset assignments found in system</h5>
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
