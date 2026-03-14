<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
            body { font-family: 'Inter', sans-serif; background-color: #f4f7f6; }
            .delete-card {
                max-width: 500px; margin: 80px auto; border-radius: 16px;
                border: none; box-shadow: 0 10px 30px rgba(0,0,0,0.08);
            }
            .icon-circle {
                width: 80px; height: 80px; background: #ffe5e5; color: #dc3545;
                border-radius: 50%; display: flex; align-items: center; justify-content: center;
                font-size: 36px; margin: 0 auto 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container">
            <div class="card delete-card text-center p-5">
                <div class="icon-circle">
                    <i class="bi bi-exclamation-triangle-fill"></i>
                </div>
                <h3 class="mb-3 fw-bold">Are you sure?</h3>
                <p class="text-muted mb-4">Do you really want to delete this user? This process cannot be undone.</p>
                <form action="<%= request.getContextPath() %>/admin/users/delete" method="post">
                    <input type="hidden" name="userToEdit" value="<%= (String) request.getAttribute("userToEdit")%>">
                    <div class="d-flex justify-content-center gap-3">
                        <a href="<%= request.getContextPath() %>/admin/users" class="btn btn-light px-4 border">Cancel</a>
                        <button type="submit" class="btn btn-danger px-4">Delete User</button>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
