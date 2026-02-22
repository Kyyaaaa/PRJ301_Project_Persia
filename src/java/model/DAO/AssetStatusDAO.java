package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AssetStatus;
import model.DBContext;
import model.View.AssetView;

public class AssetStatusDAO extends DBContext {
    
    public List<AssetStatus> getAllAssetStatuses() {
        String sql = "select * from AssetStatus";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<AssetStatus> list = new ArrayList<>();
            while (rs.next()) {
                AssetStatus i = new AssetStatus();
                i.statusId = rs.getInt("status_id");
                i.statusName = rs.getString("status_name");

                list.add(i);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
