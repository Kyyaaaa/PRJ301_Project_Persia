package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
