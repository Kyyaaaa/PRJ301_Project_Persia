<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Asset" %>
<%@page import="model.Classroom" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Classroom Management</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2><i class="bi bi-buildings me-2"></i>Classroom Management</h2>
                <a href="<%= request.getContextPath() %>/admin/classrooms/create" class="btn btn-primary">
                    <i class="bi bi-plus-circle me-1"></i> Create a new classroom
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
                            <th>Room Name</th>
                            <th>Location</th>
                            <th class="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Classroom> list = (ArrayList<Classroom>)request.getAttribute("list");
                            if(list != null && !list.isEmpty()) {
                                for(Classroom i : list) {
                        %>
                            <tr>
                                <td class="fw-bold text-muted">#<%= i.classroomId %></td>
                                <td class="fw-medium text-dark"><i class="bi bi-door-open me-2 text-secondary"></i><%= i.classroomName %></td>
                                <td><i class="bi bi-geo-alt text-danger me-1"></i><%= i.location %></td>
                                <td class="text-center action-buttons">
                                    <a href="<%= request.getContextPath() %>/admin/classrooms/edit?classroomId=<%= i.classroomId %>" class="btn btn-primary btn-sm mx-1" title="Edit Classroom">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>
                                    <a href="<%= request.getContextPath() %>/admin/classrooms/delete?classroomId=<%= i.classroomId %>" class="btn btn-danger btn-sm mx-1" title="Delete Classroom">
                                        <i class="bi bi-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="4" class="text-center text-muted py-5">
                                    <i class="bi bi-building fs-1 d-block mb-3 text-secondary"></i>
                                    <h5 class="fw-light">No classrooms found in the system</h5>
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
