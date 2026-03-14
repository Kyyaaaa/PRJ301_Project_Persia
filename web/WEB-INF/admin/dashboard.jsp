<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="model.User"%>
<%@page import="model.AssetRequest"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
            
            body {
                background-color: #f4f7f6;
                font-family: 'Inter', sans-serif;
            }
            .dashboard-card {
                border-radius: 16px;
                border: none;
                transition: all 0.3s ease;
                background: #fff;
                box-shadow: 0 4px 6px rgba(0,0,0,0.04);
                overflow: hidden;
            }
            .dashboard-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 12px 24px rgba(0,0,0,0.1);
            }
            .icon-wrapper {
                width: 64px;
                height: 64px;
                border-radius: 16px;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 28px;
                color: white;
            }
            .bg-gradient-primary { background: linear-gradient(135deg, #4e73df 0%, #224abe 100%); }
            .bg-gradient-success { background: linear-gradient(135deg, #1cc88a 0%, #13855f 100%); }
            .bg-gradient-info { background: linear-gradient(135deg, #36b9cc 0%, #258391 100%); }
            .bg-gradient-warning { background: linear-gradient(135deg, #f6c23e 0%, #dda20a 100%); }
            .bg-gradient-danger { background: linear-gradient(135deg, #e74a3b 0%, #be2617 100%); }
            .bg-gradient-dark { background: linear-gradient(135deg, #5a5c69 0%, #373840 100%); }

            .text-xs { font-size: 0.85rem; }
            .font-weight-bold { font-weight: 700!important; }
            .page-header { margin: 30px 0 25px; }
            
            .table-custom {
                background: white;
                border-radius: 12px;
                overflow: hidden;
                box-shadow: 0 4px 6px rgba(0,0,0,0.04);
            }
            .table-custom thead th {
                background: #f8f9fa;
                border-bottom: none;
                font-weight: 600;
                color: #5a5c69;
                text-transform: uppercase;
                font-size: 0.8rem;
                letter-spacing: 0.5px;
            }
            .badge-custom {
                padding: 0.5em 0.8em;
                border-radius: 6px;
                font-weight: 500;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container py-4">
            <div class="d-flex justify-content-between align-items-center page-header">
                <h2 class="h3 mb-0 text-gray-800 fw-bold">Admin Dashboard</h2>
            </div>
            
            <!-- Stats Row 1 -->
            <div class="row g-4 mb-4">
                <div class="col-xl-3 col-md-6">
                    <div class="dashboard-card h-100 p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <div>
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Total Users</div>
                                <div class="h3 mb-0 font-weight-bold text-dark">${totalUsers}</div>
                            </div>
                            <div class="icon-wrapper bg-gradient-primary shadow-sm">
                                <i class="bi bi-people-fill"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-md-6">
                    <div class="dashboard-card h-100 p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <div>
                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Total Assets</div>
                                <div class="h3 mb-0 font-weight-bold text-dark">${totalAssets}</div>
                            </div>
                            <div class="icon-wrapper bg-gradient-success shadow-sm">
                                <i class="bi bi-pc-display"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-md-6">
                    <div class="dashboard-card h-100 p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <div>
                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Total Classrooms</div>
                                <div class="h3 mb-0 font-weight-bold text-dark">${totalClassrooms}</div>
                            </div>
                            <div class="icon-wrapper bg-gradient-info shadow-sm">
                                <i class="bi bi-door-open-fill"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-md-6">
                    <div class="dashboard-card h-100 p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <div>
                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Pending Requests</div>
                                <div class="h3 mb-0 font-weight-bold text-dark">${pendingRequests}</div>
                            </div>
                            <div class="icon-wrapper bg-gradient-warning shadow-sm">
                                <i class="bi bi-bell-fill"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Stats Row 2 -->
            <div class="row g-4 mb-4">
                <div class="col-xl-6 col-md-6">
                    <div class="dashboard-card h-100 p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <div>
                                <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">Total Requests</div>
                                <div class="h3 mb-0 font-weight-bold text-dark">${totalRequests}</div>
                            </div>
                            <div class="icon-wrapper bg-gradient-danger shadow-sm">
                                <i class="bi bi-file-earmark-text-fill"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-6 col-md-6">
                    <div class="dashboard-card h-100 p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <div>
                                <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">Total Assignments</div>
                                <div class="h3 mb-0 font-weight-bold text-dark">${totalAssignments}</div>
                            </div>
                            <div class="icon-wrapper bg-gradient-dark shadow-sm">
                                <i class="bi bi-check2-square"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Recent Requests Table -->
            <div class="card dashboard-card mb-4">
                <div class="card-header bg-white py-3 d-flex flex-row align-items-center justify-content-between border-0">
                    <h6 class="m-0 font-weight-bold text-primary">Recent Requests</h6>
                    <a href="${pageContext.request.contextPath}/admin/requests" class="btn btn-sm btn-outline-primary">View All</a>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover table-custom mb-0">
                            <thead>
                                <tr>
                                    <th class="ps-4">Req #</th>
                                    <th>User</th>
                                    <th>Asset ID</th>
                                    <th>Status</th>
                                    <th class="pe-4 text-end">Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<AssetRequest> recentRequests = (List<AssetRequest>) request.getAttribute("recentRequests");
                                    if(recentRequests != null && !recentRequests.isEmpty()) {
                                        for(AssetRequest r : recentRequests) {
                                %>
                                <tr>
                                    <td class="ps-4 align-middle">#<%= r.getRequestId() %></td>
                                    <td class="align-middle fw-medium">
                                        <div class="d-flex align-items-center">
                                            <div class="rounded-circle bg-light d-flex align-items-center justify-content-center me-2" style="width: 32px; height: 32px;">
                                                <i class="bi bi-person text-secondary"></i>
                                            </div>
                                            <%= r.getRequestedBy() %>
                                        </div>
                                    </td>
                                    <td class="align-middle text-muted">A-<%= r.getAssetId() %></td>
                                    <td class="align-middle">
                                        <%
                                            String badgeClass = "bg-secondary";
                                            if("PENDING".equalsIgnoreCase(r.getStatus())) badgeClass = "bg-warning text-dark";
                                            else if("APPROVED".equalsIgnoreCase(r.getStatus())) badgeClass = "bg-success";
                                            else if("REJECTED".equalsIgnoreCase(r.getStatus())) badgeClass = "bg-danger";
                                        %>
                                        <span class="badge badge-custom <%= badgeClass %>"><%= r.getStatus() %></span>
                                    </td>
                                    <td class="pe-4 text-end align-middle text-muted"><%= r.getRequestDate() %></td>
                                </tr>
                                <%      }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="5" class="text-center py-4 text-muted">No recent requests found</td>
                                </tr>
                                <%  } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
