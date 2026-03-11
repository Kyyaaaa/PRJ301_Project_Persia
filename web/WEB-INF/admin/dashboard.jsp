<%-- 
    Document   : dashboard
    Created on : Jan 23, 2026, 1:58:22 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="/PRJ301_Project_Persia/static/css/styles.css" />
        
    </head>
    <body>
         <!-- header -->
        <jsp:include page="/WEB-INF/layout/header.jsp"/>

        <!-- sidebar -->
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp"/>

        <%
        // Vì đã có admin_filter nên chắc chắn có user
        User user = (User) session.getAttribute("user");

        int totalAssets = (Integer) request.getAttribute("totalAssets");
        int borrowedAssets = (Integer) request.getAttribute("borrowedAssets");
        int brokenAssets = (Integer) request.getAttribute("brokenAssets");
        int totalClassrooms = (Integer) request.getAttribute("totalClassrooms");
        %>
        
        <div id="main" class="main-content">

            <h2>Dashboard</h2>

            <div class="dashboard-grid">
                <div class="stat-card">
                    <span class="stat-label">Username</span>
                    <span class="stat-value text-blue"><%= user.username %></span>
                </div>
                <div class="stat-card">
                    <span class="stat-label">Role Access</span>
                    <span class="stat-value">Level <%= user.role_id %></span>
                </div>
            </div>

            <hr>

            <h2>Asset Statistics</h2>

            <div class="dashboard-grid">
                <div class="stat-card">
                    <span class="stat-label">Total Assets</span>
                    <span class="stat-value"><%= totalAssets %></span>
                </div>

                <div class="stat-card">
                    <span class="stat-label">Borrowed</span>
                    <span class="stat-value text-orange"><%= borrowedAssets %></span>
                </div>

                <div class="stat-card">
                    <span class="stat-label">Broken & Maintenance</span>
                    <span class="stat-value text-red"><%= brokenAssets %></span>
                </div>

                <div class="stat-card">
                    <span class="stat-label">Classrooms</span>
                    <span class="stat-value text-green"><%= totalClassrooms %></span>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layout/footer.jsp"/>
    </body>
</html>