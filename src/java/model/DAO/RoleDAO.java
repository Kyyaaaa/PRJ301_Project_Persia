package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AssetStatus;
import model.DBContext;
import model.Role;

public class RoleDAO extends DBContext {

    public Role getRoleById(int role_id) {

        String sql = "SELECT role_id, role_name FROM Roles WHERE role_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, role_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Role(
                    rs.getInt("role_id"),
                    rs.getString("role_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Role> getAllRoles() {
        String sql = "select * from Roles";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<Role> list = new ArrayList<>();
            while (rs.next()) {
                Role i = new Role();
                i.role_id = rs.getInt("role_id");
                i.role_name = rs.getString("role_name");

                list.add(i);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
