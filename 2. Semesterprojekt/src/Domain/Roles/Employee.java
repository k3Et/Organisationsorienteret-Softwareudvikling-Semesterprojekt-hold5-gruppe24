package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Employee extends Role {

    private static String[] permissions = {"create note", "view note", "view patient"};

    public Employee() {
        super("Employee");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Ansat";
    }

}