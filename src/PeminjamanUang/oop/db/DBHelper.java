package PeminjamanUang.oop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHelper {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB = "peminjamanuang";
    private static final String MYCONN = "jdbc:mysql://localhost/"+DB;
    private static final String SQCONN = "jdbc:sqlite:D:\\sqlitedb\\peminjamanuang.sqlite";

    public static Connection getConnection(String driver) throws SQLException {
        Connection conn = null;
        switch (driver) {
            case "SQLITE": {
                try {
                    Class.forName("org.sqlite.JDBC");
                    conn = DriverManager.getConnection(SQCONN);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DBHelper.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
                break;
            }
            case "MYSQL": {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(MYCONN, USER, PASSWORD);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DBHelper.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
                break;
            }
        }
        return conn;
    }
}
