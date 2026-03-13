<%-- 
    Document   : users
    Created on : Feb 9, 2026, 8:54:44 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="model.Role" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2><i class="bi bi-people me-2"></i>User Management</h2>
                <a href="<%= request.getContextPath() %>/admin/users/create" class="btn btn-primary">
                    <i class="bi bi-person-plus"></i> Create a new user
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
                            <th>Username</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th class="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<User> users = (ArrayList<User>)request.getAttribute("users");
                            List<Role> list_role = (ArrayList<Role>)request.getAttribute("list_role");
                            
                            Map<Integer, String> roleMap = new HashMap<>();
                            if (list_role != null) {
                                for (Role role : list_role) {
                                    roleMap.put(role.role_id, role.role_name);
                                }
                            }
                            
                            if(users != null && !users.isEmpty()) {
                                for(User user : users) {
                                    String roleName = roleMap.get(user.role_id);
                        %>
                            <tr>
                                <td class="fw-bold text-dark"><i class="bi bi-person-circle text-secondary me-2"></i><%= user.username %></td>
                                <td class="text-muted font-monospace"><%= user.password %></td>
                                <td>
                                    <% if("ADMIN".equalsIgnoreCase(roleName)) { %>
                                        <span class="badge bg-danger"><i class="bi bi-shield-lock me-1"></i><%= roleName %></span>
                                    <% } else if("MANAGER".equalsIgnoreCase(roleName)) { %>
                                        <span class="badge bg-warning text-dark"><i class="bi bi-briefcase me-1"></i><%= roleName %></span>
                                    <% } else { %>
                                        <span class="badge bg-info text-dark"><i class="bi bi-person me-1"></i><%= roleName %></span>
                                    <% } %>
                                </td>
                                <td class="text-center action-buttons">
                                    <a href="<%= request.getContextPath() %>/admin/users/edit?username=<%= user.username %>" class="btn btn-primary btn-sm mx-1" title="Edit User">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>
                                    <a href="<%= request.getContextPath() %>/admin/users/delete?username=<%= user.username %>" class="btn btn-danger btn-sm mx-1" title="Delete User">
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
                                    <i class="bi bi-people fs-1 d-block mb-3 text-secondary"></i>
                                    <h5 class="fw-light">No users found in the system</h5>
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
