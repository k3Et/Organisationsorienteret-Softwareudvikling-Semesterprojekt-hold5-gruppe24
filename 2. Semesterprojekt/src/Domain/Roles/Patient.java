package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Patient extends Role {

    String[] permissions = {};

    public Patient() {
        super("Patient");
        super.addPermissions(permissions);
    }

}