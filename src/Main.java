import dbConnection.DB_Connect;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private Statement stmt;
   private Connection conn;

    public static void main(String[] args) {


    //selectAllFromCountry();

    }
    public void selectAllFromCountry(){
        ResultSet rs = null;
        conn = DB_Connect.getInstance().getConnection();

        try {

            Statement stmt = conn.createStatement();


             rs = stmt.executeQuery("Select * from country");

            while (rs.next()) {
                System.out.println("|" + rs.getString("Code") + " | " + rs.getString("Name") + "| " + rs.getString("Continent") + "| " + rs.getString("SurfaceArea") + "| " + rs.getString("HeadOfState"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

