package Domain;

import Domain.Roles.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfEmployees {

    private static List<User> lEmployees = new ArrayList<>();

    public ListOfEmployees() {
    }

    public static void addEmployee(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Employee) {
                lEmployees.add(u);
                return;
            }
        }
        Collections.sort(lEmployees);
    }

    public static void removeEmployee(User u) {
        if (lEmployees.contains(u)) {
            lEmployees.remove(u);
        }
    }

    public static List<User> getEmployeesList() {
        return lEmployees;
    }
}