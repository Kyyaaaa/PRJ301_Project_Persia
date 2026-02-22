package model;

public class AssetType {

    public int typeId;
    public String typeName;
    public Integer categoryId;

    // Constructor không tham số
    public AssetType() {
    }

    // Constructor đầy đủ
    public AssetType(int typeId, String typeName, Integer categoryId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.categoryId = categoryId;
    }

    // Getter & Setter
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
