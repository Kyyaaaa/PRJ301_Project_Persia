<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Role" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-dark text-white py-3">
                            <h3 class="card-title mb-0"><i class="bi bi-person-plus me-2"></i>Create a New User</h3>
                        </div>
                        <div class="card-body p-4">

                            <!-- Error message -->
                            <%
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

                            <form action="<%= request.getContextPath() %>/admin/users/create" method="post" class="needs-validation">
                                
                                <!-- Username -->
                                <div class="mb-4">
                                    <label for="username" class="form-label fw-bold">Username <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light"><i class="bi bi-person"></i></span>
                                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
                                    </div>
                                </div>

                                <!-- Password -->
                                <div class="mb-4">
                                    <label for="password" class="form-label fw-bold">Password <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light"><i class="bi bi-key"></i></span>
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter secure password" required>
                                    </div>
                                </div>

                                <!-- Role -->
                                <div class="mb-4">
                                    <label for="role_id" class="form-label fw-bold">Assign Role <span class="text-danger">*</span></label>
                                    <select class="form-select" id="role_id" name="role_id" required>
                                        <%
                                            List<Role> list_role = (ArrayList<Role>)request.getAttribute("list_role");
                                            if(list_role != null) {
                                                for(Role i : list_role) {
                                        %>
                                            <option value="<%= i.role_id %>"><%= i.role_name %></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                    <div class="form-text text-muted">A role determines the user's permissions in the system.</div>
                                </div>

                                <!-- Submit -->
                                <div class="d-grid mt-5">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="bi bi-check-circle me-2"></i> Create User
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
                    
                    <div class="text-center mt-3">
                        <a href="<%= request.getContextPath() %>/admin/users" class="text-decoration-none text-muted">
                            <i class="bi bi-arrow-left"></i> Back to Users List
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
