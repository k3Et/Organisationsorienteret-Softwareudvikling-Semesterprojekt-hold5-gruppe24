package Domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Victor
 */
public class FileHandler {

    //Verify from file
    public boolean verifyLogin(String username, String password) {

        File file = new File("src/Data/LoginData.txt");
        try (Scanner scan = new Scanner(file);) {
            String tot = "";
            while (scan.hasNext()) {
                tot += scan.nextLine();
            }
            String[] data = tot.split(";");
            for (int i = 0; i < data.length; i += 2) {
                if (data[i].equals(username) && data[i + 1].equals(password)) {
                    return true;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return false;

    }

}