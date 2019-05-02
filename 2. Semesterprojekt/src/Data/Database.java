package Data;

import Domain.Controller;
import Domain.Role;
import Domain.Roles.Admin;
import Domain.Roles.Employee;
import Domain.Roles.Leader;
import Domain.Roles.Resident;
import Domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class Database {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

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

    //UPDATE DATABASE WITH LOCATION TABLE!!!
    //This method is meant to verify that the user is at the selected location at login.
    public boolean verifyLocation(String username, String location) {

        Connection locCon = null;
        PreparedStatement locPs = null;
        ResultSet locRs = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {

            locCon = DriverManager.getConnection(url, Username, Password);

            locPs = locCon.prepareStatement("SELECT * FROM Locations WHERE username = ? and location = ?");

            locPs.setString(1, username);
            locPs.setString(2, location);

            locRs = locPs.executeQuery();

            if (locRs.next()) {
                System.out.println("Location Query Accepted.");
                return true;
            } else {
                System.out.println("Location Query Failed");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                locCon.close();
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

    //This is meant to delete a user and it's roles based on the username.
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

                ps = con.prepareStatement("DELETE FROM Role WHERE username = ?");

                ps.setString(1, username);

                ps.execute();

                System.out.println("Roles deleted.");

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
                ps = con.prepareStatement("INSERT INTO Role VALUES(?, ?)");

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

    //This method is meant to create all users from the database in java, so
    //they can be added to lists and edited.
    public void loadAllUsers() {
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

    //This method is meant to check the roles of the specific user and return them in an ArrayList.
    public ArrayList getDataPermissions(User u) {

        Connection roleCon = null;
        PreparedStatement preps = null;
        ResultSet rSet = null;

        ArrayList roles = new ArrayList();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {

            roleCon = DriverManager.getConnection(url, Username, Password);

            preps = roleCon.prepareStatement("SELECT * FROM Role WHERE username = ?");

            preps.setString(1, u.getUsername());

            rSet = preps.executeQuery();

            while (rSet.next()) {
                if (rSet.getString("role").equals("Leader")) {
                    roles.add(new Leader());
                } else if (rSet.getString("role").equals("Employee")) {
                    roles.add(new Employee());
                } else if (rSet.getString("role").equals("Resident")) {
                    roles.add(new Resident());
                } else if (rSet.getString("role").equals("Admin")) {
                    roles.add(new Admin());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                roleCon.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return roles;
    }

    //implement get locations from database
    public ArrayList getLocations(User u) {

        Connection locaCon = null;
        PreparedStatement locaPs = null;
        ResultSet locaRs = null;

        ArrayList locations = new ArrayList();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {

            locaCon = DriverManager.getConnection(url, Username, Password);

            locaPs = locaCon.prepareStatement("SELECT * FROM Locations WHERE username = ?");

            locaPs.setString(1, u.getUsername());

            locaRs = locaPs.executeQuery();

            //CONTINUE HERE!!!!
            while (locaRs.next()) {
                if (locaRs.getString("location").equals("Leader")) {
                    locations.add(new Leader());
                } else if (locaRs.getString("role").equals("Employee")) {
                    locations.add(new Employee());
                } else if (locaRs.getString("role").equals("Resident")) {
                    locations.add(new Resident());
                } else if (locaRs.getString("role").equals("Admin")) {
                    locations.add(new Admin());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                locaCon.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return locations;
    }
}