package model;

public class Asset {

    public int asset_id;
    public String asset_name;
    public int category_id;
    public int quantity;
    public String status;
    public String room;
    public String note;

    public Asset() {
    }

    public Asset(int asset_id, String asset_name, int category_id,
                 int quantity, String status, String room, String note) {
        this.asset_id = asset_id;
        this.asset_name = asset_name;
        this.category_id = category_id;
        this.quantity = quantity;
        this.status = status;
        this.room = room;
        this.note = note;
    }
}