/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Roles.Patient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfPatients {

    private static List<User> list = new ArrayList<>();

    public ListOfPatients() {
    }

    public static void addPatient(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Patient) {
                list.add(u);
                return;
            }
        }
        Collections.sort(list);
    }

    public static void removePatient(User u) {
        if (list.contains(u)) {
            list.remove(u);
        }
    }

    public static List<User> getPatientList() {
        return list;
    }
}
