package Data;

import Domain.Controller;
import Domain.Role;
import Domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Domain.DiaryNote;

/**
 *
 * @author Victor
 */
public class Database {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;

    private final String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/qtbrqkid";
    private final String Username = "qtbrqkid";
    private final String Password = "ju1tuAMRQjWkEU-_Pe-5gHMzyNQfm7qa";

    //This method is meant to verify the users given logininformation.
    public boolean verifyLogin(String username, String password) {

        //Setting up the driver.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM Users WHERE username = ? and password = ?");

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            //If the query is succesful the method will return true.
            //Meaning that the logininformation exists and is correct.
            if (rs.next()) {
                System.out.println("Query Accepeted");
                return true;
            } else {
                System.out.println("Query Failed");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        return false;
    }

    //This method is meant to create a new entry/user in the database from the GUI.
    public void createUser(User u) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM Users WHERE username = ?");

            ps.setString(1, u.getUsername());

            rs = ps.executeQuery();

            //If the information isn't already in the database the user will be created.
            if (!rs.next()) {
                ps = con.prepareStatement("INSERT INTO Users VALUES(?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, u.getName());
                ps.setString(2, u.getUsername());
                ps.setString(3, u.getPassword());
                ps.setString(4, u.getCPR());
                ps.setString(5, u.getPhoneNumber());
                ps.setString(6, u.getEmail());
                ps.setString(7, u.getAddress());

                ps.execute();

                System.out.println("Row Added");
            } else {
                System.out.println("User already exists.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    public void saveNote(User employee, User resident, DiaryNote note) {
        //Setting up the driver.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM employeeNote WHERE employee = ? AND diaryNote = ?");
            ps.setString(1, employee.toString());
            ps.setString(2, note.getNote());
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = con.prepareStatement("INSERT INTO employeeNote(employee, diaryNote) VALUES (?, ?);");

                ps.setString(1, employee.toString());
                ps.setString(2, note.getNote());

                ps.execute();
            }

            ps = con.prepareStatement("SELECT * FROM residentNote WHERE resident = ? AND diaryNote = ?");
            ps.setString(1, resident.toString());
            ps.setString(2, note.getNote());
            rs2 = ps.executeQuery();
         
            if (!rs2.next()) {
                ps = con.prepareStatement("INSERT INTO residentNote(resident, diaryNote) VALUES (?, ?)");
                ps.setString(1, resident.toString());
                ps.setString(2, note.getNote());
                ps.execute();
            }
            //If the query is succesful the method will return true.
            //Meaning that the logininformation exists and is correct.
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    public void deleteUser(String username) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM Users WHERE username = ?");

            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {

                ps = con.prepareStatement("DELETE FROM Users WHERE username = ?");

                ps.setString(1, username);

                ps.execute();

                System.out.println("User deleted.");
            } else {
                System.out.println("User doesn't exist, and can't be deleted.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    //This is meant to add a user and a role to the table Role.
    public void addRole(User u, Role r) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM Role WHERE username = ? and role = ?");

            ps.setString(1, u.getUsername());
            ps.setString(2, r.getName());

            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = con.prepareStatement("INSERT INTO Role VALUES(username LIKE ?, ?)");

                ps.setString(1, u.getUsername());
                ps.setString(2, r.getName());

                ps.execute();
                System.out.println("Role added.");
            } else {
                System.out.println("The user already has that role.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    //This method is meant to delete a role. It doesn't work yet.
    public void deleteRole(User u, Role r) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM Role WHERE username = ? and role = ?");

            ps.setString(1, u.getUsername());
            ps.setString(2, r.getName());

            rs = ps.executeQuery();

            if (rs.next()) {
                ps = con.prepareStatement("DELETE FROM Role WHERE username = ? and role = ?");

                ps.setString(1, u.getUsername());
                ps.setString(2, r.getName());

                ps.execute();
                System.out.println("Role deleted.");
            } else {
                System.out.println("The user doesn't have that role.");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void fillUserList() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM Users");

            rs = ps.executeQuery();

            while (rs.next()) {
                Controller.createNewUser(rs.getString("name"), rs.getString("password"), rs.getString("username"), rs.getString("cpr"), rs.getString("phonenumber"), rs.getString("email"), rs.getString("address"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

}
