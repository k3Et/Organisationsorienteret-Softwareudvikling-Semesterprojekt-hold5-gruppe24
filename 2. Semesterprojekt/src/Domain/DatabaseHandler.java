package Domain;

import Data.Database;

/**
 *
 * @author Victor
 */
public class DatabaseHandler {

    private Database ds = new Database();

    public boolean verifyLogin(String username, String password) {
        if (ds.verifyLogin(username, password)) {
            return true;
        }
        return false;
    }

    public void createUser(User u) {
        ds.createUser(u);
    }
}
