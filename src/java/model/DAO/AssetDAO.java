package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Asset;
import model.DBContext;
import model.User;
import model.View.AssetView;

public class AssetDAO extends DBContext {
   
    public List<Asset> getAllAssets() {
        String sql = "select * from Assets";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<Asset> assets = new ArrayList<>();
            while (rs.next()) {
                Asset asset = new Asset(
                    rs.getInt("asset_id"),
                    rs.getString("asset_name"),
                    rs.getInt("type_id"),
                    rs.getInt("status_id")
                );

                assets.add(asset);
            }
            return assets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<AssetView> getAllAssetViews() {
        String sql = "select asset_id, asset_name, type_name, category_name, status_name from Assets\n" +
"inner join AssetType on Assets.type_id = AssetType.type_id\n" +
"inner join AssetStatus on Assets.status_id = AssetStatus.status_id\n" +
"inner join AssetCategory on AssetType.category_id = AssetCategory.category_id";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<AssetView> list = new ArrayList<>();
            while (rs.next()) {
                AssetView av = new AssetView();
                av.assetId = rs.getInt("asset_id");
                av.assetName = rs.getString("asset_name");
                av.typeName = rs.getString("type_name");
                av.categoryName = rs.getString("category_name");
                av.statusName = rs.getString("status_name");

                list.add(av);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public Asset create(String assetName, Integer typeId, Integer statusId) {
        String sql = """
            INSERT INTO Assets (asset_name, type_id, status_id)
            VALUES (?, ?, ?)
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS
            )
        ) {
            ps.setString(1, assetName);
            ps.setObject(2, typeId);    // setObject để xử lý NULL
            ps.setObject(3, statusId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int assetId = rs.getInt(1);
                    return new Asset(assetId, assetName, typeId, statusId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
