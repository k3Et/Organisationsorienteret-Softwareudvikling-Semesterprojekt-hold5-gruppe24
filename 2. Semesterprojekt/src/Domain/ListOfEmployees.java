/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private static List<User> list = new ArrayList<>();

    public ListOfEmployees() {
    }

    public static void addEmployee(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Employee) {
                list.add(u);
                return;
            }
        }
        Collections.sort(list);
    }

    public static void removeEmployee(User u) {
        if (list.contains(u)) {
            list.remove(u);
        }
    }

    public static List<User> getEmployeesList() {
        return list;
    }
}
