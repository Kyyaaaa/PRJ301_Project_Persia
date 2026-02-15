package utilities;

public class Validate {
    public static boolean validateUsername(String username) {
        return (username != null && username.matches("^[a-zA-Z0-9_.]{3,30}$"));
    }
    
    public static boolean validatePassword(String password) {
        return (password != null && password.matches("^[a-zA-Z0-9_.]{3,12}$"));
    }
    
    public static boolean validateRoleId(String role_id) {
        return (role_id.equals("1") || role_id.equals("2") || role_id.equals("3"));
    }
}
