package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfUsers {

    private List<User> users;

    public ListOfUsers() {
        users = new ArrayList<>();
    }

    public void addUser(User u) {
        if (!users.contains(u)) {
            users.add(u);
        }
    }

    public void removeUser(User u) {
        if (users.contains(u)) {
            users.remove(u);
        }
    }

    public List getList() {
        return users;
    }

    public User getUser(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                return users.get(i);
            }
        }
        return null;
    }
}