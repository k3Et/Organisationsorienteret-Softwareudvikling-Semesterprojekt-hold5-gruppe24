package Data;

import Domain.User;

/**
 *
 * @author Victor
 */
public class DatabaseTest {

    /**
     * This class is used for testing the methods from the Database class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Database ds = new Database();
        //ds.deleteUser("");
        //ds.deleteUser("testing");
        //ds.deleteLocation("Jakob");
        User u = new User("Victor", "data", "Tempest", "211098-1234", "54452615", "ve@gmail.com", "Adressevej 47");
        User p = new User("Jesus", "test", "Jesus", "241200-1235", "00000000", "jesus@gmail.com", "Jerusalem et sted");
        //ds.createUser(u, "København Boligcentral");
        //ds.deleteUser("SletBrugerTester");
        //ds.editTest("Victor", "Victor Elmkvist");
    }

}