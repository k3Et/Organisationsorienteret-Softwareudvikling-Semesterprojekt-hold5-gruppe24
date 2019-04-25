package Domain;

import Data.Database;

/**
 *
 * @author Victor
 */
public class DatabaseHandler {

    private Database database = new Database();

    public boolean verifyLogin(String username, String password) {
        if (database.verifyLogin(username, password)) {
            return true;
        }
        return false;
    }

    public void createUser(User u) {
        database.createUser(u);
    }
}
