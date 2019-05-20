package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Leader extends Role {

    private static String[] permissions = {"create user", "ansatte relation", "bruger rolle"};

    public Leader() {
        super("Leader");
        super.addPermissions(permissions);
    }

    public String toString() {
        return "Leder";
    }

}