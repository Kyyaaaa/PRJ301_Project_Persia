package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.DBContext;
import model.Classroom;
import model.Asset;

public class ClassroomAssetDAO extends DBContext {

    /**
     * Thêm tài sản vào phòng học
     * @param classroom_id ID phòng học
     * @param asset_id ID tài sản
     * @param quantity Số lượng
     * @param status Trạng thái
     */
    public void addClassroomAsset(int classroom_id, int asset_id, int quantity, String status) throws Exception {
        String sql = "INSERT INTO ClassroomAsset (classroom_id, asset_id, quantity, status) VALUES (?, ?, ?, ?)";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, classroom_id);
            ps.setInt(2, asset_id);
            ps.setInt(3, quantity);
            ps.setString(4, status);
            
            ps.executeUpdate();
        }
    }

    /**
     * Cập nhật thông tin tài sản trong phòng học
     * @param classroom_id ID phòng học
     * @param asset_id ID tài sản
     * @param quantity Số lượng mới
     * @param status Trạng thái mới
     */
    public void updateClassroomAsset(int classroom_id, int asset_id, int quantity, String status) throws Exception {
        String sql = "UPDATE ClassroomAsset SET quantity = ?, status = ? WHERE classroom_id = ? AND asset_id = ?";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, quantity);
            ps.setString(2, status);
            ps.setInt(3, classroom_id);
            ps.setInt(4, asset_id);
            
            ps.executeUpdate();
        }
    }

    /**
     * Xóa tài sản khỏi phòng học
     * @param classroom_id ID phòng học
     * @param asset_id ID tài sản
     */
    public void deleteClassroomAsset(int classroom_id, int asset_id) throws Exception {
        String sql = "DELETE FROM ClassroomAsset WHERE classroom_id = ? AND asset_id = ?";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, classroom_id);
            ps.setInt(2, asset_id);
            
            ps.executeUpdate();
        }
    }
    
    /**
     * Lấy danh sách tài sản trong phòng học
     * @param classroom_id ID phòng học
     * @return ResultSet chứa danh sách tài sản
     */
    public ResultSet getAssetsByClassroom(int classroom_id) throws Exception {
        String sql = "SELECT ca.asset_id, a.asset_name, a.category_id, ca.quantity, ca.status " +
                   "FROM ClassroomAsset ca " +
                   "JOIN Assets a ON ca.asset_id = a.asset_id " +
                   "WHERE ca.classroom_id = ?";
        
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, classroom_id);
        
        return ps.executeQuery();
    }
    
    /**
     * Kiểm tra tài sản đã tồn tại trong phòng học chưa
     * @param classroom_id ID phòng học
     * @param asset_id ID tài sản
     * @return true nếu tồn tại, false nếu chưa
     */
    public boolean isAssetInClassroom(int classroom_id, int asset_id) throws Exception {
        String sql = "SELECT 1 FROM ClassroomAsset WHERE classroom_id = ? AND asset_id = ?";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, classroom_id);
            ps.setInt(2, asset_id);
            
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
    
    
}
