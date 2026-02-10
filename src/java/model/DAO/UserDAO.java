package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
