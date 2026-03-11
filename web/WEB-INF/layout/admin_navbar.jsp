<%@ page contentType="text/html; charset=UTF-8" %>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/PRJ301_Project_Persia/static/css/styles.css" />

<!-- page hien tai -->
<%
String uri = request.getRequestURI();
%>

<div class="sidebar" id="sidebar">

    <div class="sidebar-title">
        User: ${sessionScope.user.username}
    </div>
    <!-- if true => highlight -->
    <a class="<%= uri.contains("dashboard") ? "active" : "" %>"
       href="${pageContext.request.contextPath}/admin/dashboard">
        Dashboard
    </a>

    <a class="<%= uri.contains("users") ? "active" : "" %>"
       href="${pageContext.request.contextPath}/admin/users">
        Users
    </a>

    <a class="<%= uri.contains("assets") ? "active" : "" %>"
       href="${pageContext.request.contextPath}/admin/assets/read">
        Assets
    </a>

    <a href="${pageContext.request.contextPath}/logout">
        Logout
    </a>

</div>