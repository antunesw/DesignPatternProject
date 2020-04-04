package dbConnection;

import java.sql.*;

/**@Author Willian Antunes de Sousa 2017226
 *
 *DB_Connect Class using Singleton Design Pattern
 *the singleton design pattern was implemented because it restricts the instantiation of this class
 *therefore permitting just one instance of it to be used in this **environment,
 */

public class DB_Connect {
    //Parameters for database access along with an instance o this class needed for the Singleton pattern design implementation

    static DB_Connect instance ;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs = null;
    private String server = "jdbc:mysql://52.50.23.197:3306/world";
    private String userName = "cctstudent";
    private String password = "Pass1234!";


    private DB_Connect() {
        //establishing connection to the server parameters server, username and password were used.
        //try catch block for handling any error that may occur when establishing connection
        try {
            conn = DriverManager.getConnection(server, userName, password);

            stmt = conn.createStatement();


        } catch (SQLException se) {
            System.out.println("SQL exception ");
            //loop through SQL exceptions
            while (se != null) {
                System.out.println("State :" + se.getSQLState());
                System.out.println("Message :" + se.getMessage());
                System.out.println("Error :" + se.getErrorCode());

                se = se.getNextException();
            }

        }
    }

    /**
     *
     * @return the instance of the connection
     */
    public static DB_Connect getInstance() {
        if (instance == null) {
            instance = new DB_Connect();
        }
        return DB_Connect.instance;
    }

    public Connection getConnection() {
        return conn;
    }

    /**
     *
     * @param query SQL query INSERT is passed as String
     * @return true or false
     */
    public boolean saveData(String query) {

        try {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     *
     * @param query   SQL query SELECT is passed as String
     * @return the Resultset from database
     */
    public ResultSet select(String query) {

        try {

            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return rs;
    }

    /**
     * it closes the connection when called
     */
    public void closing() {
        //closing the result set, statement and the connection

        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}