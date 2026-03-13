<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AssetStatus" %>
<%@page import="model.AssetType" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Asset</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-dark text-white py-3">
                            <h3 class="card-title mb-0"><i class="bi bi-box-seam me-2"></i>Create a New Asset</h3>
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
                            %>

                            <form action="<%= request.getContextPath() %>/admin/assets/create" method="post" class="needs-validation">
                                
                                <!-- Asset Name -->
                                <div class="mb-4">
                                    <label for="asset_name" class="form-label fw-bold">Asset Name <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="asset_name" name="asset_name" placeholder="Enter asset name" required>
                                </div>

                                <!-- Type -->
                                <div class="mb-4">
                                    <label for="type_id" class="form-label fw-bold">Asset Type <span class="text-danger">*</span></label>
                                    <select class="form-select" id="type_id" name="type_id" required>
                                        <option value="" selected disabled>-- Choose asset type --</option>
                                        <%
                                            List<AssetType> list_type = (ArrayList<AssetType>)request.getAttribute("list_type");
                                            if(list_type != null) {
                                                for(AssetType i : list_type) {
                                        %>
                                            <option value="<%= i.typeId %>"><%= i.typeName %></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Status -->
                                <div class="mb-4">
                                    <label for="status_id" class="form-label fw-bold">Current Status <span class="text-danger">*</span></label>
                                    <select class="form-select" id="status_id" name="status_id" required>
                                        <option value="" selected disabled>-- Select asset status --</option>
                                        <%
                                            List<AssetStatus> list_status = (ArrayList<AssetStatus>)request.getAttribute("list_status");
                                            if(list_status != null) {
                                                for(AssetStatus i : list_status) {
                                        %>
                                            <option value="<%= i.statusId %>"><%= i.statusName %></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Submit -->
                                <div class="d-grid mt-5">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="bi bi-plus-circle me-2"></i> Create Asset
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
                    
                    <div class="text-center mt-3">
                        <a href="<%= request.getContextPath() %>/admin/assets/read" class="text-decoration-none text-muted">
                            <i class="bi bi-arrow-left"></i> Back to Asset Inventory
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
