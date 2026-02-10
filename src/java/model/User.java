package model;

public class User {

    public String username;
    public String password;
    public int role_id;

    public User() {
    }

    public User(String username, String password, int role_id) {        
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }
    
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}