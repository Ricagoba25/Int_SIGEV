package mx.edu.utez.sigev.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConector {
    static final String DBNAME = "sigev";
    static final String USER = "root";
    static final String PASSWORD = "rootadmin";
    static final String TIMEZONE = "America/Mexico_City";
    static final String USESSL = "false";
    static final String PUBLICKEY = "true";
    static final String DDLAUTO = "true";
    static final String HOST = "localhost";

    public static Connection connect() {
        String dataSource =
                String.format("jdbc:mysql://%s:3306/%s?user=%s&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s&createDatabaseIfNotExist= %s", HOST, DBNAME, USER, PASSWORD, TIMEZONE, USESSL, PUBLICKEY, DDLAUTO);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}