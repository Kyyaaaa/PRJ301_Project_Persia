package model;

public class AssetStatus {

    public int statusId;
    public String statusName;

    // Constructor không tham số
    public AssetStatus() {
    }

    // Constructor đầy đủ
    public AssetStatus(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    // Getter & Setter
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
