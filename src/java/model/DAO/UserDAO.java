package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DBContext;
import model.User;

public class UserDAO extends DBContext {

    /**
     * Login: kiểm tra username & password
     * @return User nếu đúng, null nếu sai
     */
    public User login(String username, String password) {

        String sql = """
            SELECT username, password, role_id
            FROM Users
            WHERE username = ? AND password = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("role_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("role_id")
                ));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Register: Đăng ký user mới gồm (username, password, role_id)
     * @return User nếu đăng ký thành công, null nếu không đăng ký thành công
     */
    public User register(String username, String password, int role_id) {
        String sql = """
            INSERT INTO Users(username, password, role_id) 
            VALUES (?, ?, ?);
        """;
        
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, role_id);

            int rs = ps.executeUpdate();

            if (rs > 0) {
                return new User(username, password, role_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

/**
     * Update: Cập nhật thông tin user (password, role) dựa trên username
     * @param user Đối tượng User chứa thông tin mới
     * @return true nếu update thành công, false nếu thất bại
     */
    public boolean update(String username, String password, int role_id) {
        String sql = """
            UPDATE Users 
            SET password = ?, role_id = ? 
            WHERE username = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            // Set các giá trị mới
            ps.setString(1, password);
            ps.setInt(2, role_id); // Giả sử model User có hàm getRoleId()
            
            // Set điều kiện Where (Username cũ)
            ps.setString(3, username);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

/**
     * Delete: Xóa user khỏi hệ thống dựa trên username
     * @param username Tên đăng nhập cần xóa
     * @return true nếu xóa thành công, false nếu thất bại (hoặc không tìm thấy)
     */
    public boolean delete(String username) {
        String sql = "DELETE FROM Users WHERE username = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            // Gán giá trị cho dấu hỏi chấm (?)
            ps.setString(1, username);

            // executeUpdate trả về số dòng bị ảnh hưởng
            int rowsAffected = ps.executeUpdate();
            
            // Nếu số dòng > 0 nghĩa là đã có user bị xóa
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Kiểm tra user có tồn tại hay không
     */
    public boolean isExist(String username) {
        String sql = "SELECT 1 FROM Users WHERE username = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
