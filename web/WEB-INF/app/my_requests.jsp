<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@page import="model.AssetRequest" %>
        <%@page import="java.util.List" %>
            <%@page import="java.text.SimpleDateFormat" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>Asset Requests</title>
                </head>

                <body>
                    <jsp:include page="/WEB-INF/layout/app_navbar.jsp" />

                    <% String success=(String) request.getAttribute("success"); if (success !=null) { %>
                        <p style="color:green;">
                            <%= success %>
                        </p>
                        <% } String error=(String) request.getAttribute("error"); if (error !=null) { %>
                            <p style="color:red;">
                                <%= error %>
                            </p>
                            <% } List<AssetRequest> listAssetRequest =
                                (List<AssetRequest>) request.getAttribute("listAssetRequest");

                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    %>

                                    <table border="1" cellpadding="6" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Asset ID</th>
                                                <th>Classroom ID</th>
                                                <th>Requested By</th>
                                                <th>Purpose</th>
                                                <th>Request Date</th>
                                                <th>Expected Return</th>
                                                <th>Status</th>
                                                <th>Reviewed By</th>
                                                <th>Reviewed Date</th>
                                                <th>Review Note</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (listAssetRequest !=null && !listAssetRequest.isEmpty()) { for
                                                (AssetRequest r : listAssetRequest) { %>
                                                <tr>
                                                    <td>
                                                        <%= r.getRequestId() %>
                                                    </td>
                                                    <td>
                                                        <%= r.getAssetId() %>
                                                    </td>
                                                    <td>
                                                        <%= r.getClassroomId() %>
                                                    </td>
                                                    <td>
                                                        <%= r.getRequestedBy() %>
                                                    </td>
                                                    <td>
                                                        <%= r.getPurpose() !=null ? r.getPurpose() : "" %>
                                                    </td>
                                                    <td>
                                                        <%= r.getRequestDate() !=null ? sdf.format(r.getRequestDate())
                                                            : "" %>
                                                    </td>
                                                    <td>
                                                        <%= r.getExpectedReturnDate() !=null ?
                                                            sdf.format(r.getExpectedReturnDate()) : "" %>
                                                    </td>
                                                    <td>
                                                        <%= r.getStatus() %>
                                                    </td>
                                                    <td>
                                                        <%= r.getReviewedBy() !=null ? r.getReviewedBy() : "" %>
                                                    </td>
                                                    <td>
                                                        <%= r.getReviewedDate() !=null ? sdf.format(r.getReviewedDate())
                                                            : "" %>
                                                    </td>
                                                    <td>
                                                        <%= r.getReviewNote() !=null ? r.getReviewNote() : "" %>
                                                    </td>
                                                    <td>
                                                        <% if ("PENDING".equals(r.getStatus())) { %>
                                                            <a
                                                                href="<%= request.getContextPath() %>/app/my-requests/delete?requestId=<%= r.getRequestId() %>">
                                                                <button type="button">Delete</button>
                                                            </a>
                                                            <% } %>
                                                    </td>
                                                </tr>
                                                <% } } else { %>
                                                    <tr>
                                                        <td colspan="12" style="text-align:center;">
                                                            No asset requests found.
                                                        </td>
                                                    </tr>
                                                    <% } %>
                                        </tbody>
                                    </table>

                </body>

                </html>