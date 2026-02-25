package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AssetStatus;
import model.AssetType;
import model.DBContext;

public class AssetTypeDAO extends DBContext {
    
    public List<AssetType> getAllAssetTypes() {
        String sql = "select * from AssetType";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<AssetType> list = new ArrayList<>();
            while (rs.next()) {
                AssetType i = new AssetType();
                i.typeId = rs.getInt("type_id");
                i.typeName = rs.getString("type_name");
                i.categoryId = rs.getInt("category_id");
                
                list.add(i);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
