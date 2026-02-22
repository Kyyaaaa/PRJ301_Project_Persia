package model;

public class Asset {

    public int assetId;
    public String assetName;
    public Integer typeId;
    public Integer statusId;

    // Constructor không tham số
    public Asset() {
    }

    // Constructor đầy đủ tham số
    public Asset(int assetId, String assetName, Integer typeId, Integer statusId) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.typeId = typeId;
        this.statusId = statusId;
    }

    // Getter & Setter
    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}