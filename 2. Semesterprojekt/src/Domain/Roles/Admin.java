package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Admin extends Role {

    String[] permissions = {};

    public Admin() {
        super("Admin");
        super.addPermissions(permissions);
    }


}

    public String toString(){
        return "Admin";
    }
}

