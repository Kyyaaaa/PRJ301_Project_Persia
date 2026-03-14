<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Asset Management System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap');
            
            body {
                font-family: 'Inter', sans-serif;
                background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                margin: 0;
            }
            .login-container {
                width: 100%;
                max-width: 1000px;
                background: #fff;
                border-radius: 20px;
                box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                display: flex;
                animation: fadeUp 0.6s ease-out;
            }
            @keyframes fadeUp {
                from { opacity: 0; transform: translateY(30px); }
                to { opacity: 1; transform: translateY(0); }
            }
            .login-image {
                flex: 1;
                background: linear-gradient(135deg, rgba(78, 115, 223, 0.9) 0%, rgba(34, 74, 190, 0.9) 100%), url('https://images.unsplash.com/photo-1497215728101-856f4ea42174?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80') center/cover;
                display: flex;
                flex-direction: column;
                justify-content: center;
                padding: 4rem;
                color: white;
            }
            .login-image h2 {
                font-weight: 700;
                font-size: 2.5rem;
                margin-bottom: 1rem;
            }
            .login-image p {
                font-weight: 300;
                font-size: 1.1rem;
                opacity: 0.9;
                line-height: 1.6;
            }
            .login-form-wrapper {
                flex: 1;
                padding: 4rem;
                display: flex;
                flex-direction: column;
                justify-content: center;
                background: #ffffff;
            }
            .login-header h3 {
                font-weight: 700;
                color: #2c3e50;
                margin-bottom: 0.5rem;
            }
            .login-header p {
                color: #7f8c8d;
                margin-bottom: 2rem;
            }
            .form-floating > .form-control {
                border-radius: 12px;
                border: 1px solid #e0e6ed;
                padding-left: 1rem;
            }
            .form-floating > .form-control:focus {
                border-color: #4e73df;
                box-shadow: 0 0 0 0.25rem rgba(78, 115, 223, 0.1);
            }
            .form-floating > label {
                padding-left: 1rem;
            }
            .btn-primary {
                background: #4e73df;
                border: none;
                border-radius: 12px;
                padding: 0.8rem;
                font-weight: 600;
                letter-spacing: 0.5px;
                transition: all 0.3s ease;
            }
            .btn-primary:hover {
                background: #224abe;
                transform: translateY(-2px);
                box-shadow: 0 8px 15px rgba(78, 115, 223, 0.3);
            }
            .alert-custom {
                border-radius: 10px;
                border: none;
                background: #ffe5e5;
                color: #d63031;
                font-weight: 500;
                font-size: 0.9rem;
            }
            .input-group-text {
                background: transparent;
                border: none;
                position: absolute;
                right: 15px;
                top: 50%;
                transform: translateY(-50%);
                z-index: 10;
                color: #a0aec0;
            }
            @media (max-width: 768px) {
                .login-container {
                    flex-direction: column;
                    max-width: 450px;
                    margin: 1.5rem;
                }
                .login-image {
                    padding: 3rem 2rem;
                    text-align: center;
                }
                .login-form-wrapper {
                    padding: 3rem 2rem;
                }
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <div class="login-image">
                <h2>Asset Management</h2>
                <p>A comprehensive system to track, request, and manage assets efficiently across your entire organization.</p>
            </div>
            <div class="login-form-wrapper">
                <div class="login-header">
                    <h3>Welcome Back!</h3>
                    <p>Please enter your credentials to log in.</p>
                </div>
                
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                    <div class="alert alert-custom alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i><%= error %>
                        <button type="button" class="btn-close btn-sm" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <%
                    }
                %>

                <form action="<%= request.getContextPath() %>/login" method="post">
                    <div class="form-floating mb-3 position-relative">
                        <input type="text" class="form-control" id="floatingUsername" name="username" placeholder="Username" required>
                        <label for="floatingUsername">Username</label>
                        <i class="bi bi-person input-group-text"></i>
                    </div>
                    <div class="form-floating mb-4 position-relative">
                        <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" required>
                        <label for="floatingPassword">Password</label>
                        <i class="bi bi-lock input-group-text"></i>
                    </div>
                    
                    <button class="btn btn-primary w-100" type="submit">Log In</button>
                    
                    <div class="text-center mt-4">
                        <span class="text-muted" style="font-size: 0.85rem;">PRJ301 Project Persia &copy; 2026 Admin System</span>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
