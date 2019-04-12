package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Patient extends Role {

    private String[] permissions = {};

    public Patient() {
        super("Patient");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Patient";
    }

}
