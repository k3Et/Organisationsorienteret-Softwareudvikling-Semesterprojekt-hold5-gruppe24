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

    private static Date date;

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

    public static List<Role> getDataPermissions(User u) {
        return database.getDataPermissions(u);
    }

    public static void saveNoteInDatabase(String employee, User resident, DiaryNote note, String date) {
        database.saveNote(employee, resident, date, note);
    }

    //Til at gemme datoen som String
    public static String convertDate() {
        date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateTime = dateFormat.format(date);
        return dateTime;
    }

    public static List<String> getEmployeeNote(String employee, String date) {
        return database.getEmployeeNote(employee, date);
    }

    public static List<String> getResidentNote(String resident, String date) {
        return database.getResidentNote(resident, date);
    }

    public static void deleteNote(String date) {
        database.deleteNote(date);
    }
}