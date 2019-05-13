package Data;

import Domain.Controller;
import Domain.ListOfUsers;
import Domain.Role;
import Domain.Roles.Admin;
import Domain.Roles.Employee;
import Domain.Roles.Leader;
import Domain.Roles.Resident;
import Domain.User;
import static GUI.LoginFXMLController.currentLocation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Domain.DiaryNote;
import Domain.ListOfEmployees;
import Domain.ListOfResidents;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean verifyLogin(String username, String password, String location) {

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
            //And that the input location is correct.
            if (rs.next() && verifyLocation(username, location)) {
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

            //If the query returns a row, the user exists at the location, and the method will return True.
            if (locRs.next()) {
                System.out.println("Verify Location Query Accepted.");
                return true;
            } else {
                System.out.println("Verify Location Query Failed");
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
    public void createUser(User u, String location) {

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
            //If the User doesn't already have the currentLocation, it will be created.
            if (!rs.next() && createLocation(u, location)) {
                ps = con.prepareStatement("INSERT INTO Users VALUES(?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, u.getName());
                ps.setString(2, u.getUsername());
                ps.setString(3, u.getPassword());
                ps.setString(4, u.getCPR());
                ps.setString(5, u.getPhoneNumber());
                ps.setString(6, u.getEmail());
                ps.setString(7, u.getAddress());

                ps.execute();

                System.out.println("User Row Added");
                //If the user exists, and is at the currentLocation, nothing will be created.
            }
            if (verifyLocation(u.getUsername(), location)) {
                System.out.println("User already exists, and is at the currentLocation.");

                //If the user exists, and isn't at the current location, the location will be created.
            } else {
                createLocation(u, location);
                System.out.println("Username already exists. Location added to User.");
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
            //If the query returns a row, the user exists at the location, and it will be deleted.
            if (rs.next()) {

                //If deleteLocation returns 1, the user only exists at one location
                //and both the user and the location will be deleted.
                //If the deleteLocation doesn't return 1, the user exists at more
                //than one location, and only the location will be deleted,
                //so the user still can be loaded at the other locations.
                if (deleteLocation(username) == 1) {
                    ps = con.prepareStatement("DELETE FROM Users WHERE username = ?");

                    ps.setString(1, username);

                    ps.execute();

                    System.out.println("User deleted.");

                    //When the user is deleted, it's roles has to be deleted too.
                    ps = con.prepareStatement("DELETE FROM Role WHERE username = ?");

                    ps.setString(1, username);

                    ps.execute();

                    System.out.println("Roles deleted.");
                }
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

            //If the query doesn't return a row, the role doesn't exist
            //and will be created.
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

    //This method is meant to delete a role.
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

            //If the query returns a row, the role exists and can and will be deleted.
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

            //Clearing list so there won't be duplicates.
            ListOfUsers.clear();
            ListOfEmployees.getEmployeesList().clear();
            ListOfResidents.getResidentList().clear();

            while (rs.next()) {
                //Checks if the user is at the logged in/currentLocation
                //so that only the users at the currentLocation will be loaded.
                if (verifyLocation(rs.getString("username"), currentLocation)) {
                    Controller.createNewUser(rs.getString("name"), rs.getString("password"), rs.getString("username"), rs.getString("cpr"), rs.getString("phonenumber"), rs.getString("email"), rs.getString("address"));
                }
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
    public List<Role> getDataPermissions(User u) {

        Connection roleCon = null;
        PreparedStatement preps = null;
        ResultSet rSet = null;

        List<Role> roles = new ArrayList<>();

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

            //Checks for roles in the database. If the strings match, the matching
            //role will be created.
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

    //Create location when user is created.
    public boolean createLocation(User u, String location) {

        Connection locaCon = null;
        PreparedStatement locaPs = null;
        ResultSet locaRs = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            locaCon = DriverManager.getConnection(url, Username, Password);

            locaPs = locaCon.prepareStatement("SELECT * FROM Locations WHERE username = ? and location = ?");

            locaPs.setString(1, u.getUsername());
            locaPs.setString(2, location);

            locaRs = locaPs.executeQuery();

            //If the information isn't already in the database the userlocation will be created.
            if (!locaRs.next()) {
                locaPs = locaCon.prepareStatement("INSERT INTO Locations VALUES(?, ?)");

                locaPs.setString(1, u.getUsername());
                locaPs.setString(2, location);

                locaPs.execute();

                System.out.println("Location Row Added");
                return true;
            } else {
                System.out.println("User already at this location.");
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
        return false;
    }

    //Deleting the current users location, when a user is deleted
    //without deleting all user locations.
    public int deleteLocation(String username) {

        Connection locaCon = null;
        PreparedStatement locaPs = null;
        ResultSet locaRs = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            locaCon = DriverManager.getConnection(url, Username, Password);

            locaPs = locaCon.prepareStatement("SELECT * FROM Locations WHERE username = ?", locaRs.TYPE_SCROLL_INSENSITIVE, locaRs.CONCUR_UPDATABLE);

            locaPs.setString(1, username);

            locaRs = locaPs.executeQuery();

            //If there is more than one location saved at the user, only the currentLocation will be deleted.
            //If there is one row the method will return one. If there are more it will return the rownumber.
            locaRs.last();
            if (locaRs.getRow() == 1) {

                locaPs = locaCon.prepareStatement("DELETE FROM Locations WHERE username = ?");

                locaPs.setString(1, username);

                locaPs.execute();

                System.out.println("Final Location Deleted.");
                return 1;
            } else if (locaRs.getRow() > 1) {

                int returner = locaRs.getRow();

                locaPs = locaCon.prepareStatement("DELETE FROM Locations WHERE username = ? and location = ?");

                locaPs.setString(1, username);
                locaPs.setString(2, currentLocation);

                locaPs.execute();

                System.out.println("This Location Deleted.");
                return returner;
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
        return 0;
    }

    //This method is meant to download all the locations for the users and return them
    //in an ArrayList. The method is used when a userobject is created at login.
    //STILL NOT IMPLEMENTED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

            while (locaRs.next()) {
                locations.add(locaRs.getString("location"));
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

    public void saveNote(String employee, User resident, String date, DiaryNote note) {
        //Setting up the driver.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            con = DriverManager.getConnection(url, Username, Password);

            ps = con.prepareStatement("SELECT * FROM employeeNote WHERE employee = ? AND note = ?");
            ps.setString(1, employee);
            ps.setString(2, note.getNote());
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = con.prepareStatement("INSERT INTO employeeNote VALUES (?, ?, ?)");
                ps.setString(3, date);
                ps.setString(1, employee);
                ps.setString(2, note.getNote());

                ps.execute();
                System.out.println("SQL 1DONE");
            }

            ps = con.prepareStatement("SELECT * FROM residentNote WHERE resident = ? AND note = ?");
            ps.setString(1, resident.toString());
            ps.setString(2, note.getNote());
            rs2 = ps.executeQuery();

            if (!rs2.next()) {
                ps = con.prepareStatement("INSERT INTO residentNote VALUES (?, ?, ?)");
                ps.setString(3, date);
                ps.setString(2, resident.toString());
                ps.setString(1, note.getNote());
                ps.execute();
                System.out.println("SQL 2DONE");
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

    public List<String> getEmployeeNote(String employee, String date) {

        List<String> listOfNotes = new ArrayList<>();
        rs = null;
        //Setting up the driver.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        try {

            con = DriverManager.getConnection(url, Username, Password);
            ps = con.prepareStatement("SELECT note FROM employeeNote WHERE employee = ? ORDER BY date");
            //SELECT note FROM employeeNote WHERE employee = ? AND note LIKE ?
            ps.setString(1, employee);
            //ps.setString(2, note.getNote());
            ps.setString(2, date);
            // System.out.println("%" +date +"%");

            rs = ps.executeQuery();
            // System.out.println(rs.getString("note"));

            System.out.println("Note selected from DB");
            while (rs.next()) {

                listOfNotes.add(rs.getString("note"));
            }

            System.out.println("Note added to listOfNote from DB");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return listOfNotes;
    }

    public void deleteNote(String date) {
        rs = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        try {
            con = DriverManager.getConnection(url, Username, Password);
            ps = con.prepareStatement("DELETE FROM residentNote WHERE date LIKE ? ");
            ps.setString(1,"%"+date+"%");
            System.out.println("IN DATABASE: "+'%'+date+'%');
            if (date != null) {
                ps.execute();
                System.out.println("Note Deleted in resident table");
            }

            ps = con.prepareStatement("DELETE FROM employeeNote WHERE date LIKE ? ");
            ps.setString(1, "%"+date+"%");

            if (date != null) {
                ps.execute();
                System.out.println("Note Deleted in employee table");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<String> getResidentNote(String resident, String date) {

        List<String> listOfNotes = new ArrayList<>();
        rs = null;
        //Setting up the driver.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        try {

            con = DriverManager.getConnection(url, Username, Password);
            ps = con.prepareStatement("SELECT note FROM residentNote WHERE resident = ? ORDER BY date ");
            //SELECT note FROM employeeNote WHERE employee = ? AND note LIKE ?
            ps.setString(1, resident);
            //ps.setString(2, date);
            // System.out.println("%" +date +"%");

            rs = ps.executeQuery();
            // System.out.println(rs.getString("note"));

            System.out.println("Note selected from DB");
            while (rs.next()) {

                listOfNotes.add(rs.getString("note"));
            }

            System.out.println("Note added to listOfNote from DB");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return listOfNotes;
    }

}