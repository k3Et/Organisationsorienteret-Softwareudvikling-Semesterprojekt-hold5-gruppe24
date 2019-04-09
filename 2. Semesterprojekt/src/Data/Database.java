package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Victor
 */
public class Database {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/qtbrqkid";
    String Username = "qtbrqkid";
    String Password = "ju1tuAMRQjWkEU-_Pe-5gHMzyNQfm7qa";

    public boolean verifyLogin(String username, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM Login WHERE username = ? and password = ?");

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Query Accepeted");
                ps.close();
                rs.close();
                con.close();
                return true;
            } else {
                System.out.println("Query Failed");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

}
