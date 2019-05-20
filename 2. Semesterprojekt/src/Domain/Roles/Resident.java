package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Resident extends Role {

    private static String[] permissions = {};

    public Resident() {
        super("Resident");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Borger";
    }

}