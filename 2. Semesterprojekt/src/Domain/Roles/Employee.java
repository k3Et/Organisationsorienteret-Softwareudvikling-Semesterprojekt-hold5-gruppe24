package Domain.Roles;

import Domain.Role;
import Domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class Employee extends Role {

    private static String[] permissions = {"create note", "view note", "view patient"};
    private List<User> relations = new ArrayList<>();

    public Employee() {
        super("Employee");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Employee";
    }

    public void addRelation(User u) {
        for (Role r : u.getRoles()) {
            if (r instanceof Patient && !relations.contains(u)) {
                relations.add(u);
            }
        }
    }

    public void removeRelation(User u) {
        if (relations.contains(u)) {
            relations.remove(u);
        }
    }

    public List<User> getRelations() {
        return relations;
    }
}
