package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */

public class Leader extends Role {

    private String[] permissions = {"create user"};

    public Leader() {
        super("Leader");
        super.addPermissions(permissions);
    }

    
    public String toString(){
        return "Leader";
    }
}

