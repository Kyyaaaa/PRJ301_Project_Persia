    package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AssetAssignment;
import model.DBContext;

public class AssetAssignmentDAO extends DBContext {
    
    public List<AssetAssignment> getAllAssetAssignments() {
        String sql = "select * from AssetAssignment";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<AssetAssignment> list = new ArrayList<>();
            while (rs.next()) {
                AssetAssignment i = new AssetAssignment(
                    rs.getInt("assignment_id"),
                    rs.getInt("asset_id"),
                    rs.getInt("classroom_id"),
                    rs.getDate("assigned_date"),
                    rs.getDate("return_date"),
                    rs.getString("assigned_by")
                );

                list.add(i);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<AssetAssignment> getAllFromUser(String username) {
        String sql = "select * from AssetAssignment where assigned_by = ?";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            List<AssetAssignment> list = new ArrayList<>();
            while (rs.next()) {
                AssetAssignment i = new AssetAssignment(
                    rs.getInt("assignment_id"),
                    rs.getInt("asset_id"),
                    rs.getInt("classroom_id"),
                    rs.getDate("assigned_date"),
                    rs.getDate("return_date"),
                    rs.getString("assigned_by")
                );

                list.add(i);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(int assignment_id) {
        String sql = "delete from AssetAssignment where assignment_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            // Gán giá trị cho dấu hỏi chấm (?)
            ps.setInt(1, assignment_id);

            // executeUpdate trả về số dòng bị ảnh hưởng
            int rowsAffected = ps.executeUpdate();
            
            // Nếu số dòng > 0 nghĩa là đã có user bị xóa
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean create(int assetId, int classroomId, java.util.Date assignedDate, java.util.Date returnDate, String assignedBy) {
        String sql = """
            INSERT INTO AssetAssignment (asset_id, classroom_id, assigned_date, return_date, assigned_by)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, assetId);
            ps.setInt(2, classroomId);
            ps.setDate(3, assignedDate != null ? new java.sql.Date(assignedDate.getTime()) : null);
            ps.setDate(4, returnDate != null ? new java.sql.Date(returnDate.getTime()) : null);
            ps.setString(5, assignedBy);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isAssetCurrentlyAssigned(int assetId) {
        String sql = "SELECT 1 FROM AssetAssignment WHERE asset_id = ?";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, assetId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
