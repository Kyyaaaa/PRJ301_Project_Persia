package model;

import java.util.Date;

public class AssetRequest {

    // ===== Fields (map trực tiếp với DB) =====
    public int requestId;
    public int assetId;
    public int classroomId;
    public String requestedBy;
    public String purpose;
    public Date requestDate;
    public Date expectedReturnDate;
    public String status;
    public String reviewedBy;
    public Date reviewedDate;
    public String reviewNote;

    // ===== Constructor không tham số =====
    public AssetRequest() {
    }

    // ===== Constructor đầy đủ tham số =====
    public AssetRequest(
            int requestId,
            int assetId,
            int classroomId,
            String requestedBy,
            String purpose,
            Date requestDate,
            Date expectedReturnDate,
            String status,
            String reviewedBy,
            Date reviewedDate,
            String reviewNote
    ) {
        this.requestId = requestId;
        this.assetId = assetId;
        this.classroomId = classroomId;
        this.requestedBy = requestedBy;
        this.purpose = purpose;
        this.requestDate = requestDate;
        this.expectedReturnDate = expectedReturnDate;
        this.status = status;
        this.reviewedBy = reviewedBy;
        this.reviewedDate = reviewedDate;
        this.reviewNote = reviewNote;
    }

    // ===== Getter & Setter =====
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public Date getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public String getReviewNote() {
        return reviewNote;
    }

    public void setReviewNote(String reviewNote) {
        this.reviewNote = reviewNote;
    }
}