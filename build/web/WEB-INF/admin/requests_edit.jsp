<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review Request</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />

        <div class="container container-fluid mt-4 mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-dark text-white py-3">
                            <h3 class="card-title mb-0"><i class="bi bi-clipboard-check me-2"></i>Review Asset Request</h3>
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

                                String success = (String) request.getAttribute("success");
                                if (success != null) {
                            %>
                                <div class="alert alert-success alert-dismissible fade show" role="alert">
                                    <i class="bi bi-check-circle me-1"></i> <%= success %>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            <%
                                }
                            %>

                            <form action="<%= request.getContextPath() %>/admin/requests/edit" method="post" class="needs-validation">
                                
                                <!-- Request ID (Visual) -->
                                <div class="mb-4 d-flex justify-content-between align-items-center bg-light p-3 rounded border">
                                    <div>
                                        <span class="text-muted small fw-bold text-uppercase">Request ID</span>
                                        <h5 class="mb-0 text-primary">#<%= request.getAttribute("requestToEdit") %></h5>
                                    </div>
                                    <i class="bi bi-envelope-open fs-2 text-secondary opacity-50"></i>
                                </div>
                                <input type="hidden" name="requestToEdit" value="<%= request.getAttribute("requestToEdit") %>">

                                <!-- Action -->
                                <div class="mb-4">
                                    <label for="status" class="form-label fw-bold">Review Decision <span class="text-danger">*</span></label>
                                    <select class="form-select border-primary" id="status" name="status" required>
                                        <option value="APPROVE" class="text-success fw-bold">✔️ Approve</option>
                                        <option value="REJECT" class="text-danger fw-bold">❌ Reject</option>
                                    </select>
                                </div>

                                <!-- Review Note -->
                                <div class="mb-4">
                                    <label for="reviewNote" class="form-label fw-bold">Review Note (Optional)</label>
                                    <textarea class="form-control" id="reviewNote" name="reviewNote" rows="3" maxlength="255" 
                                              placeholder="Provide a reason or note for the requester..."></textarea>
                                </div>

                                <!-- Submit -->
                                <div class="d-grid mt-5">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="bi bi-send-check me-2"></i> Submit Review
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
                    
                    <div class="text-center mt-3">
                        <a href="<%= request.getContextPath() %>/admin/requests" class="text-decoration-none text-muted">
                            <i class="bi bi-arrow-left"></i> Back to Requests List
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>