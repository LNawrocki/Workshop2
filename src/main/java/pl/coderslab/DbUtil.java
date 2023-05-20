package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static final String DB_URL = "jdbc:mysql://192.168.200.202:3306/workshop2?serverTimezone=UTC";
    public static final String DB_USER = "lukasz";
    public static final String DB_PASS = "Coderslab@0!3";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    };


}
