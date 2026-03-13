<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.View.AssetView" %>
<%@page import="model.Classroom" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Asset</title>
    </head>
    <body>

        <jsp:include page="/WEB-INF/layout/app_navbar.jsp" />

        <div class="container container-fluid mt-4 mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-8 col-md-10">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-primary text-white py-3">
                            <h3 class="card-title mb-0"><i class="bi bi-cart-plus me-2"></i>Request an Asset</h3>
                        </div>
                        <div class="card-body p-4">

                            <!-- Success message -->
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
                            %>

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

                            <%
                                List<AssetView> listAssetView =
                                    (ArrayList<AssetView>) request.getAttribute("listAssetView");
                                List<Classroom> listClassroom =
                                    (ArrayList<Classroom>) request.getAttribute("listClassroom");
                            %>

                            <form action="<%= request.getContextPath() %>/app/assets/request" method="post" class="needs-validation">
                                
                                <!-- Asset -->
                                <div class="mb-4">
                                    <label for="assetId" class="form-label fw-bold">Select Asset <span class="text-danger">*</span></label>
                                    <select class="form-select border-primary" id="assetId" name="assetId" required>
                                        <option value="" selected disabled>-- Choose an asset to request --</option>
                                        <%
                                            if (listAssetView != null) {
                                                for (AssetView a : listAssetView) {
                                        %>
                                            <option value="<%= a.getAssetId() %>">
                                                <%= a.getAssetName() %>
                                                ( <%= a.getTypeName() %> - <%= a.getStatusName() %> )
                                            </option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                    <div class="form-text text-muted">Select the asset you want to assign to a classroom.</div>
                                </div>

                                <!-- Classroom -->
                                <div class="mb-4">
                                    <label for="classroomId" class="form-label fw-bold">Assign to Classroom <span class="text-danger">*</span></label>
                                    <select class="form-select border-primary" id="classroomId" name="classroomId" required>
                                        <option value="" selected disabled>-- Choose a classroom --</option>
                                        <%
                                            if (listClassroom != null) {
                                                for (Classroom c : listClassroom) {
                                        %>
                                            <option value="<%= c.getClassroomId() %>">
                                                <%= c.getClassroomName() %> 
                                                <%= c.getLocation() != null ? "- " + c.getLocation() : "" %>
                                            </option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Purpose -->
                                <div class="mb-4">
                                    <label for="purpose" class="form-label fw-bold">Purpose of Request</label>
                                    <textarea class="form-control focus-ring focus-ring-primary" id="purpose" name="purpose" rows="4" 
                                              placeholder="Briefly explain why you need this asset..."></textarea>
                                </div>

                                <!-- Expected return date -->
                                <div class="mb-4">
                                    <label for="expectedReturnDate" class="form-label fw-bold">Expected Return Date</label>
                                    <input type="date" class="form-control" id="expectedReturnDate" name="expectedReturnDate">
                                </div>

                                <!-- Submit -->
                                <div class="d-grid mt-5">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="bi bi-send me-2"></i> Submit Request
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
                    
                    <div class="text-center mt-3">
                        <a href="<%= request.getContextPath() %>/app/assets" class="text-decoration-none text-muted">
                            <i class="bi bi-arrow-left"></i> Back to Assets List
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>