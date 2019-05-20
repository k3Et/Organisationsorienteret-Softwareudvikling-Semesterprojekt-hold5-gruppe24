package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Admin extends Role {

    private static String[] permissions = {"bruger rolle"};

    public Admin() {
        super("Admin");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Admin";
    }

}