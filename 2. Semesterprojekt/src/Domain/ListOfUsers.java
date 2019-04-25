package Domain;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Patrick
 */
public class ListOfUsers {

    private List<User> lUsers;

    public ListOfUsers() {
        lUsers = new ArrayList<>();
    }

    public void addUser(User u) {
        if (!lUsers.contains(u)) {
            lUsers.add(u);
        }
    }

    public void removeUser(User u) {
        if (lUsers.contains(u)) {
            lUsers.remove(u);
        }
    }

    public List getList() {
        return lUsers;
    }

    public User getUser(String username, String password) {
        for (int i = 0; i < lUsers.size(); i++) {
            if (lUsers.get(i).getUsername().equals(username) && lUsers.get(i).getPassword().equals(password)) {
                return lUsers.get(i);
            }
        }
        return null;
    }
}
