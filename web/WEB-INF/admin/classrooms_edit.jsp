<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Classroom" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Classroom</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-dark text-white py-3">
                            <h3 class="card-title mb-0"><i class="bi bi-pencil-square me-2"></i>Edit Classroom Info</h3>
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
                            
                            <% Classroom currentClassroom = (Classroom)request.getAttribute("currentClassroom"); %>

                            <form action="<%= request.getContextPath() %>/admin/classrooms/edit" method="post" class="needs-validation">
                                
                                <!-- Classroom To Edit (Hidden ID & Visual display) -->
                                <div class="mb-4 d-flex justify-content-between align-items-center bg-light p-3 rounded border">
                                    <div>
                                        <span class="text-muted small fw-bold text-uppercase">Classroom ID</span>
                                        <h5 class="mb-0 text-primary">#<%= (String) request.getAttribute("classroomToEdit") %></h5>
                                    </div>
                                    <i class="bi bi-building fs-2 text-secondary opacity-50"></i>
                                </div>
                                <input type="hidden" name="classroomToEdit" value="<%= (String) request.getAttribute("classroomToEdit")%>">

                                <!-- Room Name -->
                                <div class="mb-4">
                                    <label for="classroom_name" class="form-label fw-bold">Room Name <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light"><i class="bi bi-door-open"></i></span>
                                        <input type="text" class="form-control" id="classroom_name" name="classroom_name" 
                                               value="<%= currentClassroom != null ? currentClassroom.getClassroomName() : "" %>" required>
                                    </div>
                                </div>

                                <!-- Location -->
                                <div class="mb-4">
                                    <label for="location" class="form-label fw-bold">Location / Branch <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light"><i class="bi bi-geo-alt"></i></span>
                                        <input type="text" class="form-control" id="location" name="location" 
                                               value="<%= currentClassroom != null ? currentClassroom.getLocation() : "" %>" required>
                                    </div>
                                </div>

                                <!-- Submit -->
                                <div class="d-grid mt-5">
                                    <button type="submit" class="btn btn-warning btn-lg text-dark fw-bold">
                                        <i class="bi bi-save me-2"></i> Save Changes
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
                    
                    <div class="text-center mt-3">
                        <a href="<%= request.getContextPath() %>/admin/classrooms/read" class="text-decoration-none text-muted">
                            <i class="bi bi-arrow-left"></i> Back to Classroom List
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
