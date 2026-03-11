<%@ page contentType="text/html; charset=UTF-8" %>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/styles.css">

<header>
    <div class="topbar">
        <div class="topbar-left">
            <span class="topbar-title">Asset Management System</span>
        </div>

        <div class="topbar-right">
            <span class="user-role-badge">
                <i class="fas fa-user-shield"></i> ${sessionScope.user.role_name}
            </span>

            <a href="${pageContext.request.contextPath}/logout" class="logout-icon" title="Logout">
                <i class="fas fa-sign-out-alt"></i>
            </a>
        </div>
    </div>
</header>
