package Domain;

import Domain.Roles.Employee;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class User implements Comparable<User> {

    private String name;
    private String password;
    private String username;
    private String CPR;
    private String phoneNumber;
    private String email;
    private String address;
    private ListOfRoles roles;

    private ListOfRelations relations;

    public User(String name, String password, String username, String CPR, String phoneNumber, String email, String address) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.CPR = CPR;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;

        roles = new ListOfRoles();
        relations = new ListOfRelations();
    }

    public String getCPR() {
        return CPR;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

//    public User(Role r){
//        roles = new ListOfRoles(r);
//    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void createNote() {
        if (roles.getPermissions().contains("create note")) {
            System.out.println("created note!");
        } else {
            System.out.println("Do not have permission!");
        }
    }

    //Creates user to the login-system with username and password. Includes some proof of concept ATM.
    public String createUser(User u) {
        if (getPermissions().contains("create user")) {
            File file = new File("src/Data/LoginData.txt");
            try (Scanner scan = new Scanner(file); FileWriter fw = new FileWriter(file, true);) {
                String uName = u.getUsername();
                String pWord = u.getPassword();
                String tot = "";
                while (scan.hasNext()) {
                    tot += scan.nextLine();
                }
                String[] data = tot.split(";");
                for (int i = 0; i < data.length; i += 2) {
                    if (data[i].equals(uName)) {
                        return "User already exists";
                    }
                }
                fw.append(uName + ";" + pWord + ";\n");
                return "User created successfully";
            } catch (IOException ex) {
                System.out.println(ex.getStackTrace());
                return "Exception: IOException";
            }
        }
        return "Not permitted";
    }

    public List<Role> getRoles() {
        return roles.getRoleList();
    }

    public ListOfRoles getRoleList() {

        return roles;
    }

    //public List<Locations> getLocations() {
    //return location.getLocationList();
    //}
    @Override
    public String toString() {
        return name;
    }

    public String toStringAll() {
        return "Navn: " + name + ";" + "CPR: " + CPR + ";" + "Mobil: " + phoneNumber + ";" + "Email: " + email + ";" + "Adresse: " + address + ";" + "Rolle: " + roles + " ";
    }

    @Override
    public int compareTo(User o) {
        return getName().compareTo(o.getName());
    }

    //Returns all permissions that the user has. Each permission is saved as a string in an index of the list.
    public List<String> getPermissions() {
        return roles.getPermissions();
    }

    public List<User> getRelations() {
        for (Role r : getRoles()) {
            if (r instanceof Employee) {

                return relations.getlRelations();
            }
        }
        return null;
    }

    public List<User> getUnrelated() {
        for (Role r : getRoles()) {
            if (r instanceof Employee) {
                return relations.getUnrelated();

            }
        }
        return null;
    }

}