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

    private List<User> relations = new ArrayList<>();

    public void addRelation(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Resident && !relations.contains(u)) {
                relations.add(u);
            }
        }
    }

    public void removeRelation(User u) {
        relations.remove(u);
    }

    public List<User> getRelations() {
        return relations;
    }

    public List<User> getUnrelated() {
        List<User> allPatients = new ArrayList<>();
        allPatients.addAll(ListOfResidents.getResidentList());
        for (User u : getRelations()) {
            allPatients.remove(u);
        }
        return allPatients;
    }
}
