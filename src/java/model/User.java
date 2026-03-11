package model;

public class User {

    public String username;
    public String password;
    public int role_id;
    public String role_name;
    public User() {
    }

    public User(String username, String password, int role_id, String role_name) {        
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.role_name = role_name;
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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

}