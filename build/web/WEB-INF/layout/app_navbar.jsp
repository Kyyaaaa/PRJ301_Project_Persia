<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Global Bootstrap & Styles inject -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
<style>
    <%@ include file="/WEB-INF/styles/global.css" %>
</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4 shadow-sm">
    <div class="container-fluid container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/app/dashboard"><i class="bi bi-laptop"></i> Persia App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#appNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="appNavbar">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/app/dashboard">Dashboard</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/app/assets">Assets</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/app/classrooms">Classrooms</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/app/my-requests">My requests</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/app/asset-assignments">Asset assignments</a></li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link text-warning" href="${pageContext.request.contextPath}/logout"><i class="bi bi-box-arrow-right"></i> Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
