package Domain;

import Domain.Roles.Resident;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfRelations {

    private List<User> lRelations = new ArrayList<>();

    public void addRelation(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Resident && !lRelations.contains(u)) {
                lRelations.add(u);
            }
        }
    }

    public void removeRelation(User u) {
        lRelations.remove(u);
    }

    public List<User> getlRelations() {
        return lRelations;
    }

    public List<User> getUnrelated() {
        List<User> allPatients = new ArrayList<>();
        allPatients.addAll(ListOfResidents.getResidentList());
        for (User u : getlRelations()) {
            allPatients.remove(u);
        }
        return allPatients;
    }
}
