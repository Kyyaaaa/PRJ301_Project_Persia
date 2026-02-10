package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;"
                   + "databaseName=PRJ_1;"
                   + "encrypt=true;"
                   + "trustServerCertificate=true";
        String user = "sa";
        String password = "123";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }
    
    // ==== TEST ====
    public static void main(String[] args) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            System.out.println("KẾT NỐI SQL SERVER THÀNH CÔNG");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
