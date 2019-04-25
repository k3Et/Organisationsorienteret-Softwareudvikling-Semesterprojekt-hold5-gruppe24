package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfRoles {

    // er rolle listen pr person! ikke systemet
    private List<Role> lRoles;

    public ListOfRoles() {
        lRoles = new ArrayList<>();
    }

    public ListOfRoles(Role r) {
        lRoles = new ArrayList<>();
        lRoles.add(r);
    }

    public void addRole(Role r) {
        lRoles.add(r);
    }

    public void removeRole(Role r) {

        lRoles.remove(r);
    }

//Returns list where each index contains a string that is equal to a permission
    public List<String> getPermissions() {

        String permissions = "";
        for (Role r : lRoles) {
            permissions += r.getPermissions();
        }
        String[] permissionsArray = permissions.split(":");
        List<String> list = new ArrayList<>();
        for (String s : permissionsArray) {
            list.add(s);
        }
        return list;
    }

    public List<Role> getRoleList() {
        return lRoles;
    }

    public String toString() {
        return String.valueOf(lRoles);
    }

}
