<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Asset Assignment</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
            body { font-family: 'Inter', sans-serif; background-color: #f4f7f6; }
            .delete-card {
                max-width: 500px; margin: 40px auto; border-radius: 16px;
                border: none; box-shadow: 0 10px 30px rgba(0,0,0,0.08);
            }
            .icon-circle {
                width: 80px; height: 80px; background: #ffe5e5; color: #dc3545;
                border-radius: 50%; display: flex; align-items: center; justify-content: center;
                font-size: 36px; margin: 0 auto 20px;
            }
            .alert-custom { border-radius: 10px; border: none; font-weight: 500; }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container">
            <div class="d-flex justify-content-center">
                <div style="max-width: 500px; width: 100%; margin-top: 30px;">
                    <%
                        String success = (String) request.getAttribute("success");
                        if (success != null) {
                    %>
                        <div class="alert alert-success alert-custom alert-dismissible fade show" role="alert">
                            <i class="bi bi-check-circle-fill me-2"></i><%= success %>
                            <button type="button" class="btn-close btn-sm" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    <%
                        }
                    %>
                    
                    <%
                        String error = (String) request.getAttribute("error");
                        if (error != null) {
                    %>
                        <div class="alert alert-danger alert-custom alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i><%= error %>
                            <button type="button" class="btn-close btn-sm" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    <%
                        }
                    %>
                </div>
            </div>

            <div class="card delete-card text-center p-5 mt-0">
                <div class="icon-circle">
                    <i class="bi bi-x-circle"></i>
                </div>
                <h3 class="mb-3 fw-bold">Cancel Assignment?</h3>
                <p class="text-muted mb-4">Are you sure you want to delete this asset assignment? This allows the asset to be reassigned.</p>
                <form action="<%= request.getContextPath() %>/admin/asset-assignments/delete" method="post">
                    <input type="hidden" name="assetAssignmentToEdit" value="<%= (String) request.getAttribute("assetAssignmentToEdit")%>">
                    <div class="d-flex justify-content-center gap-3">
                        <a href="<%= request.getContextPath() %>/admin/asset-assignments" class="btn btn-light px-4 border">Cancel</a>
                        <button type="submit" class="btn btn-danger px-4">Delete Assignment</button>
                    </div>
                </form>
            </div>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
