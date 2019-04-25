/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Roles.Resident;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfRelations {

    private List<User> list = new ArrayList<>();

    public void addRelation(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Resident && !list.contains(u)) {
                list.add(u);
            }
        }
    }

    public void removeRelation(User u) {
        list.remove(u);
    }

    public List<User> getList() {
        return list;
    }

    public List<User> getUnrelated() {
        List<User> allPatients = new ArrayList<>();
        allPatients.addAll(ListOfResidents.getResidentList());
        for (User u : getList()) {
            allPatients.remove(u);
        }
        return allPatients;
    }
}
