package Domain;

import Data.Database;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Victor
 */
public class DatabaseHandler {

    private Date date;

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

    public static void loadAllUsers() {
        database.fillUserList();
    }

    public static void saveNoteInDatabase(String employee, User resident, DiaryNote note) {
        database.saveNote(employee, resident, note);
    }

    //Til at gemme datoen som String
    public String convertDate() {
        date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        String dateTime = dateFormat.format(date);
        return dateTime;
    }

    public List<String> getNote(String employee, String date) {
         System.out.println("foark");
        return database.getNote(employee, date);
    }
}
