<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-dark text-white py-3">
                            <h3 class="card-title mb-0"><i class="bi bi-pencil-square me-2"></i>Edit a User</h3>
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

                            <form action="<%= request.getContextPath() %>/admin/users/edit" method="post" class="needs-validation">
                                
                                <div class="mb-4">
                                    <label class="form-label fw-bold text-muted">Editing User:</label>
                                    <div class="fs-5 fw-bold text-primary">
                                        <i class="bi bi-person-badge me-2"></i><%= (String) request.getAttribute("userToEdit") %>
                                    </div>
                                    <input type="hidden" name="userToEdit" value="<%= (String) request.getAttribute("userToEdit")%>">
                                </div>

                                <!-- Password -->
                                <div class="mb-4">
                                    <label for="password" class="form-label fw-bold">New Password <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light"><i class="bi bi-key"></i></span>
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter new password" required>
                                    </div>
                                </div>

                                <!-- Role -->
                                <div class="mb-4">
                                    <label for="role_id" class="form-label fw-bold">New Role <span class="text-danger">*</span></label>
                                    <select class="form-select" id="role_id" name="role_id">
                                        <%
                                            model.User oldUser = (model.User) request.getAttribute("oldUser");
                                            int roleId = oldUser != null ? oldUser.getRole_id() : -1;
                                        %>
                                        <option value="1" <%= roleId == 1 ? "selected" : "" %>>Admin</option>
                                        <option value="2" <%= roleId == 2 ? "selected" : "" %>>Manager/Teacher</option>
                                        <option value="3" <%= roleId == 3 ? "selected" : "" %>>Student</option>
                                    </select>
                                </div>

                                <!-- Submit -->
                                <div class="d-grid mt-5">
                                    <button type="submit" class="btn btn-warning btn-lg text-dark fw-bold">
                                        <i class="bi bi-cloud-arrow-up me-2"></i> Update User
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
