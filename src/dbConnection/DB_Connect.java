package dbConnection;

import java.sql.*;


public class DB_Connect {

    private static DB_Connect instance;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs = null;
    private String server = "jdbc:mysql://52.50.23.197:3306/world";
    private String userName = "cctstudent";
    private String password = "Pass1234!";

    /**
     * //to de done: private constructor
     */
    public DB_Connect() {
        //

        try {
            conn = DriverManager.getConnection(server, userName, password);

            stmt = conn.createStatement();


        } catch (SQLException se) {
            System.out.println("SQL exceptiom ");
            //loop through SQL exceptions
            while (se != null) {
                System.out.println("State :" + se.getSQLState());
                System.out.println("Message :" + se.getMessage());
                System.out.println("Error :" + se.getErrorCode());

                se = se.getNextException();
            }

        }
    }

//    public static DB_Connect getInstance() {
//        if (instance == null) {
//            instance = new DB_Connect();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return conn;
//    }


    public boolean saveData(String query) {

        try {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public ResultSet select(String query) {

        try {

            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return rs;
    }

    /**
     * close connection when it's called
     */
    public void closing() {
        //closing the result set, statement and the connection

        try {
            rs.close();
            stmt.close();
            conn.close();
            //check the correct way of closing (conn)
            // DB_Connect.getInstance().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}