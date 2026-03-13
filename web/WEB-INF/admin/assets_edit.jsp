<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AssetStatus" %>
<%@page import="model.AssetType" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Asset</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layout/admin_navbar.jsp" />
        
        <div class="container container-fluid mt-4 mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-dark text-white py-3">
                            <h3 class="card-title mb-0"><i class="bi bi-boxes me-2"></i>Edit Asset Details</h3>
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
                            
                            <%@page import="model.Asset" %>
                            <% Asset currentAsset = (Asset)request.getAttribute("currentAsset"); %>

                            <form action="<%= request.getContextPath() %>/admin/assets/edit" method="post" class="needs-validation">
                                
                                <!-- Asset To Edit (Hidden & Visual) -->
                                <div class="mb-4 d-flex justify-content-between align-items-center bg-light p-3 rounded border">
                                    <div>
                                        <span class="text-muted small fw-bold text-uppercase">Asset ID</span>
                                        <h5 class="mb-0 text-primary">#<%= (String) request.getAttribute("assetToEdit") %></h5>
                                    </div>
                                    <i class="bi bi-qr-code fs-2 text-secondary opacity-50"></i>
                                </div>
                                <input type="hidden" name="assetToEdit" value="<%= (String) request.getAttribute("assetToEdit")%>">

                                <!-- Asset Name -->
                                <div class="mb-4">
                                    <label for="asset_name" class="form-label fw-bold">Asset Name <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="asset_name" name="asset_name" 
                                           value="<%= currentAsset != null ? currentAsset.getAssetName() : "" %>" required>
                                </div>

                                <!-- Type -->
                                <div class="mb-4">
                                    <label for="type_id" class="form-label fw-bold">Asset Type <span class="text-danger">*</span></label>
                                    <select class="form-select" id="type_id" name="type_id" required>
                                        <%
                                            List<AssetType> list_type = (ArrayList<AssetType>)request.getAttribute("list_type");
                                            if(list_type != null) {
                                                for(AssetType i : list_type) {
                                                    String selected = (currentAsset != null && currentAsset.getTypeId() == i.typeId) ? "selected" : "";
                                        %>
                                            <option value="<%= i.typeId %>" <%= selected %>><%= i.typeName %></option>
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
                                        <%
                                            List<AssetStatus> list_status = (ArrayList<AssetStatus>)request.getAttribute("list_status");
                                            if(list_status != null) {
                                                for(AssetStatus i : list_status) {
                                                    String selected = (currentAsset != null && currentAsset.getStatusId() == i.statusId) ? "selected" : "";
                                        %>
                                            <option value="<%= i.statusId %>" <%= selected %>><%= i.statusName %></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Submit -->
                                <div class="d-grid mt-5">
                                    <button type="submit" class="btn btn-warning btn-lg text-dark fw-bold">
                                        <i class="bi bi-save me-2"></i> Save Changes
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
