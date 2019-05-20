package Domain;

import Data.DatabaseHandler;
import Domain.Roles.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class Controller {

    static ListOfUsers users = new ListOfUsers();
    static List<Role> listOfRoles = new ArrayList<>();

    public static List<User> getStockUsers() {
        return users.getList();
    }

    public static void setStockRoleList() {
        listOfRoles.add(new Employee());
        listOfRoles.add(new Resident());
        listOfRoles.add(new Leader());
        listOfRoles.add(new Admin());
    }

    public static List getStockRoleList() {
        return listOfRoles;
    }

    public static void addRoleToUser(User selectedUser, Role selectedRole) {
        if (!selectedUser.getRoles().contains(selectedRole)) {
            selectedUser.getRoleList().addRole(selectedRole);
            if (selectedRole instanceof Resident) {
                ListOfResidents.addResident(selectedUser);
            } else if (selectedRole instanceof Employee) {
                ListOfEmployees.addEmployee(selectedUser);
            }
        }
    }

    public static void removeRoleFromUser(User selectedUser, Role selectedRole) {
        if (selectedUser.getRoles().contains(selectedRole)) {
            selectedUser.getRoleList().removeRole(selectedRole);
            if (selectedRole instanceof Resident) {
                ListOfResidents.removeResident(selectedUser);
            } else if (selectedRole instanceof Employee) {
                ListOfEmployees.removeEmployee(selectedUser);
            }
        }
    }

    public static void createNewUser(String name, String password, String username, String CPR, String phoneNumber, String email, String address) {
        User user = new User(name, password, username, CPR, phoneNumber, email, address);
        user.getRoles().addAll(Controller.getDataPermissions(user));
        for (Role r : user.getRoles()) {
            if (r instanceof Resident) {
                ListOfResidents.addResident(user);
            } else if (r instanceof Employee) {
                ListOfEmployees.addEmployee(user);
            }
        }
        ListOfUsers.addUser(user);
    }

    public static void createUserInDatabase(String name, String password, String username, String CPR, String phoneNumber, String email, String address, String location) {
        User user = new User(name, password, username, CPR, phoneNumber, email, address);
        ListOfUsers.addUser(user);
        DatabaseHandler.createUser(user, location);
    }

    public static User findLoggedUser(String username) {

        for (User u : ListOfUsers.getList()) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public static void saveNote(String user, User selectedUser, String note, String date) {
        DatabaseHandler.saveNoteInDatabase(user, selectedUser, new DiaryNote(note), date);
    }

    public static void editNote(String date, String newNote) {
        DatabaseHandler.editNote(date, newNote);
    }

    //Database calls:
    public static boolean verifyLogin(String username, String password, String location) {
        if (DatabaseHandler.verifyLogin(username, password, location)) {
            return true;
        }
        return false;
    }

    public static void deleteUser(String username) {
        DatabaseHandler.deleteUser(username);
    }

    public static void loadAllUsers() {
        DatabaseHandler.loadAllUsers();
    }

    public static void addRole(User u, Role r) {
        DatabaseHandler.addRole(u, r);
    }

    public static void deleteRole(User u, Role r) {
        DatabaseHandler.deleteRole(u, r);
    }

    public static List<Role> getDataPermissions(User u) {
        return DatabaseHandler.getDataPermissions(u);
    }

}
