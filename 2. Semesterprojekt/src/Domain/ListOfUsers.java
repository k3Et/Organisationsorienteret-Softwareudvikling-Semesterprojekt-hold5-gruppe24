package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfUsers {

    private static List<User> lUsers;

    public ListOfUsers() {
        lUsers = new ArrayList<>();
    }

    public static void addUser(User u) {
        for (User us : lUsers) {
            if (us.getUsername().equals(u.getUsername()) || us.getUsername() == null) {
                return;
            }
        }
        lUsers.add(u);
    }

    public static void removeUser(User u) {
        if (lUsers.contains(u)) {
            lUsers.remove(u);
        }
    }

    public static List<User> getList() {
        return lUsers;
    }

    public static User getUser(String username, String password) {
        for (int i = 0; i < lUsers.size(); i++) {
            if (lUsers.get(i).getUsername().equals(username) && lUsers.get(i).getPassword().equals(password)) {
                return lUsers.get(i);
            }
        }
        return null;
    }

    public static void clear() {
        lUsers.clear();
    }
}
