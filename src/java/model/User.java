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
}