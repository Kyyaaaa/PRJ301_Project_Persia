package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DBContext;
import model.Classroom;

public class ClassroomDAO extends DBContext {
    
    // Lấy danh sách phòng
    public List<Classroom> getAllClassrooms() {

        String sql = "SELECT * FROM Classrooms";

        List<Classroom> list = new ArrayList<>();

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                Classroom c = new Classroom(
                        rs.getInt("classroom_id"),
                        rs.getString("room_code"),
                        rs.getString("room_name"),
                        rs.getString("note")
                );

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Đếm tổng số phòng
    public int getTotalClassrooms() {

        String sql = "SELECT COUNT(*) FROM Classrooms";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    // Thêm phòng
    public boolean addClassroom(String roomCode, String roomName, String note) {

        String sql = "INSERT INTO Classrooms (room_code, room_name, note) VALUES (?, ?, ?)";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, roomCode);
            ps.setString(2, roomName);
            ps.setString(3, note);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật phòng
    public boolean updateClassroom(int classroomId, String roomCode, String roomName, String note) {

        String sql = "UPDATE Classrooms SET room_code = ?, room_name = ?, note = ? WHERE classroom_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, roomCode);
            ps.setString(2, roomName);
            ps.setString(3, note);
            ps.setInt(4, classroomId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xóa phòng
    public boolean deleteClassroom(int classroomId) {

        String sql = "DELETE FROM Classrooms WHERE classroom_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, classroomId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    
}