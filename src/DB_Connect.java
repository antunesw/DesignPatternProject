import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class DB_Connect {

    private static String IP;
    private static DB_Connect instance;

    private Connection conn;

    private String databaseName;
    private String UserName;
    private String password;


    private DB_Connect() {
        IP = "52.50.23.197";
        databaseName = "world";
        UserName = "cctstudent";
        password = "Pass1234!";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/" + databaseName + "?user=" + UserName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DB_Connect getInstance() {
        if (instance == null) {
            instance = new DB_Connect();
        }
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }


}