package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Leader extends Role {

    private static String[] permissions = {"create user", "ansatte relation", "borger relation"};

    public Leader() {
        super("Leader");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Leder";
    }

}
