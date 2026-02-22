package model.View;

public class AssetView {

    public int assetId;
    public String assetName;
    public String typeName;
    public String categoryName;
    public String statusName;

    // Constructor không tham số
    public AssetView() {
    }

    // Constructor đầy đủ
    public AssetView(int assetId, String assetName, String typeName, String categoryName, String statusName) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.typeName = typeName;
        this.categoryName = categoryName;
        this.statusName = statusName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
