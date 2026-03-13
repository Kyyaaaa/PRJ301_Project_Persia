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
    
    public Asset update(int assetId, String assetName, Integer typeId, Integer statusId) {
        String sql = """
            UPDATE Assets
            SET asset_name = ?, type_id = ?, status_id = ?
            WHERE asset_id = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, assetName);
            ps.setObject(2, typeId);
            ps.setObject(3, statusId);
            ps.setInt(4, assetId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return new Asset(assetId, assetName, typeId, statusId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean isExist(String assetId) {
        String sql = "SELECT 1 FROM Assets WHERE asset_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, assetId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Asset findById(String assetId) {
        String sql = "SELECT * FROM Assets WHERE asset_id = ?";
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, assetId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Asset(
                    rs.getInt("asset_id"),
                    rs.getString("asset_name"),
                    rs.getInt("type_id"),
                    rs.getInt("status_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(String assetId) {
        String sql = "DELETE FROM Assets WHERE asset_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            // Gán giá trị cho dấu hỏi chấm (?)
            ps.setString(1, assetId);

            // executeUpdate trả về số dòng bị ảnh hưởng
            int rowsAffected = ps.executeUpdate();
            
            // Nếu số dòng > 0 nghĩa là đã có user bị xóa
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
