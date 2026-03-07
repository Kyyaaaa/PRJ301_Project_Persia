package model;

import java.util.Date;

public class AssetAssignment {

    public int assignmentId;
    public int assetId;
    public int classroomId;
    public Date assignedDate;
    public Date returnDate;
    public String assignedBy;

    public AssetAssignment() {
    }

    public AssetAssignment(int assignmentId, int assetId, int classroomId,
                           Date assignedDate, Date returnDate, String assignedBy) {
        this.assignmentId = assignmentId;
        this.assetId = assetId;
        this.classroomId = classroomId;
        this.assignedDate = assignedDate;
        this.returnDate = returnDate;
        this.assignedBy = assignedBy;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
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

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }
}