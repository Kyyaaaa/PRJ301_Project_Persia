package model;

import java.sql.Connection;
import java.sql.DriverManager;

import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class DBContext {

    public Connection getConnection() throws Exception {

        Properties props = new Properties();

        try (InputStream input =
                DBContext.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException(
                    "Không tìm thấy db.properties trong classpath");
            }

            props.load(input);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }
    

}
