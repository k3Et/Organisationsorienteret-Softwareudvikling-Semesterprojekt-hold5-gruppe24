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
        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).equals(r)){

                roles.remove(i);
            }
        }

//        roles.remove(r);
    }

    //Returns list where each index contains a string that is equal to a permission
    public List getPermissions() {
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

}


    public List getRoleList() {
        return roles;
    }

    public String toString() {
        return String.valueOf(roles);
    }
}

