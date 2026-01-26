package model;

public class User {

    private String username;
    private String password;
    private int role_id;

    // Constructor rỗng (bắt buộc)
    public User() {
    }

    // Constructor đầy đủ
    public User(String username, String password, int role_id) {        
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    // Getter & Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return role_id;
    }
}