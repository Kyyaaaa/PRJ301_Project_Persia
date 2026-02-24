package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Classroom;
import model.DBContext;

public class ClassroomDAO extends DBContext {
    
    public List<Classroom> getAllClassrooms() {
        String sql = "select * from Classroom";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<Classroom> list = new ArrayList<>();
            while (rs.next()) {
                Classroom i = new Classroom(
                    rs.getInt("classroom_id"),
                    rs.getString("classroom_name"),
                    rs.getString("location")
                );

                list.add(i);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public Classroom create(String classroomName, String location) {
        String sql = """
            INSERT INTO Classroom (classroom_name, location)
            VALUES (?, ?)
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS
            )
        ) {
            ps.setObject(1, classroomName);    // setObject để xử lý NULL
            ps.setObject(2, location);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int classroomId = rs.getInt(1);
                    return new Classroom(classroomId, classroomName, location);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public Classroom update(int classroomId, String classroomName, String location) {
        String sql = """
            UPDATE Classroom
            SET classroom_name = ?, location = ?
            WHERE classroom_id = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, classroomName); // NOT NULL
            ps.setObject(2, location);      // NULL allowed
            ps.setInt(3, classroomId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
            return new Classroom(classroomId, classroomName, location);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean isExist(String classroomId) {
        String sql = "SELECT 1 FROM Classroom WHERE classroom_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, classroomId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
