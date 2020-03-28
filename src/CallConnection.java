import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class CallConnection {
    public static void main(String[] args) {

      //  Connection c = DB_Connect.getInstance().getConnection();
        Connection c1 = DB_Connect.getInstance().getConnection();
        Connection c3 = DB_Connect.getInstance().getConnection();

        try {
            Statement st = c1.createStatement();


            ResultSet rs = st.executeQuery("Select * from country");

            while(rs.next()){
                System.out.println(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


  }
}
