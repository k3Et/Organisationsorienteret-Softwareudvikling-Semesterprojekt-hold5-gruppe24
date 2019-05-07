package Domain;

import Data.Database;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class DatabaseHandler {

    private static Database database = new Database();

    public static boolean verifyLogin(String username, String password, String location) {
        if (database.verifyLogin(username, password, location)) {
            return true;
        }
        return false;
    }

    public static void createUser(User u, String location) {
        database.createUser(u, location);
    }

    public static void deleteUser(String username) {
        database.deleteUser(username);
    }

    public static void loadAllUsers() {
        database.loadAllUsers();
    }

    public static void addRole(User u, Role r) {
        database.addRole(u, r);
    }

    public static void deleteRole(User u, Role r) {
        database.deleteRole(u, r);
    }

    public static ArrayList getDataPermissions(User u) {
        return database.getDataPermissions(u);
    }

}