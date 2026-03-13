<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Classroom" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Classrooms</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/app_navbar.jsp" />
        
        <div class="container container-fluid mt-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2>Classrooms</h2>
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
                
            <div class="table-wrapper table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Room name</th>
                            <th>Location</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Classroom> list = (ArrayList<Classroom>)request.getAttribute("list");
                            if(list != null && !list.isEmpty()) {
                                for(Classroom i : list) {
                        %>
                            <tr>
                                <td><%= i.classroomId %></td>
                                <td><i class="bi bi-door-open me-2 text-secondary"></i><%= i.classroomName %></td>
                                <td><i class="bi bi-geo-alt me-2 text-danger"></i><%= i.location %></td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="3" class="text-center text-muted py-4">
                                    <i class="bi bi-building fs-2 d-block mb-2"></i>
                                    No classrooms found.
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
