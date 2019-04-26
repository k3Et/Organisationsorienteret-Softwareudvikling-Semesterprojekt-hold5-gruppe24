package Domain;

import Data.Database;

/**
 *
 * @author Victor
 */
public class DatabaseHandler {

    private static Database database = new Database();

    public static boolean verifyLogin(String username, String password) {
        if (database.verifyLogin(username, password)) {
            return true;
        }
        return false;
    }

    public static void createUser(User u) {
        database.createUser(u);
    }
    
    public static void loadAllUsers(){
        database.fillUserList();
    }
}