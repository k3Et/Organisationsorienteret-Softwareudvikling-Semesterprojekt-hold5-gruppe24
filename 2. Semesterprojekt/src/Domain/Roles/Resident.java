package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Resident extends Role {

    private String[] permissions = {};

    public Resident() {
        super("Patient");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Borger";
    }

}
