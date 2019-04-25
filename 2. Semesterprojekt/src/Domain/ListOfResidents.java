/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Roles.Resident;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfResidents {

    private static List<User> lResidents = new ArrayList<>();

    public ListOfResidents() {
    }

    public static void addResident(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Resident) {
                lResidents.add(u);
                return;
            }
        }
        Collections.sort(lResidents);
    }

    public static void removeResident(User u) {
        if (lResidents.contains(u)) {
            lResidents.remove(u);
        }
    }

    public static List<User> getResidentList() {
        return lResidents;
    }
}
