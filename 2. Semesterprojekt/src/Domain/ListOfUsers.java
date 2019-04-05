/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

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
    
    public List getList(){
        return users;
    }
}
