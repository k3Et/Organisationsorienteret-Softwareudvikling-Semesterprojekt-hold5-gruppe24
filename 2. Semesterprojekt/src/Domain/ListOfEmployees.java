/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Roles.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfEmployees {

    private List<User> list;

    public ListOfEmployees() {
        list = new ArrayList<>();
    }

    public void addEmployee(User u) {
        for (Object r : u.getRoles()) {
            if ((Role) r instanceof Employee) {
                list.add(u);
                return;
            }
        }
        Collections.sort(list);
    }

    public void removeEmployee(User u) {
        if (list.contains(u)) {
            list.remove(u);
        }
    }

    public List<User> getEmployeesList() {
        return list;
    }
}
