package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class RoleList {

    // er rolle listen pr person! ikke systemet
    private List<Role> roles;

    public RoleList() {
        roles = new ArrayList<>();
    }

    public RoleList(Role r) {
        roles = new ArrayList<>();
        roles.add(r);
    }

    public void addRole(Role r) {
        roles.add(r);
    }


    public void removeRole(Role r) {

        roles.remove(r);
    }

//Returns list where each index contains a string that is equal to a permission
    public List<String> getPermissions() {

        String permissions = "";
        for (Role r : roles) {
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
        return roles;
    }

    public String toString() {
        return String.valueOf(roles);
    }

}
