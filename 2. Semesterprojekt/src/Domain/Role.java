package Domain;

/**
 *
 * @author Patrick
 */
public abstract class Role {


    private String roleName;

    private StringBuilder permissions;

//    public Role(){
//        permissions = new StringBuilder();
//    }
    public Role(String roleName) {
        this.roleName = roleName;
        permissions = new StringBuilder();
    }

    public String getName() {
        return roleName;
    }

    //Adds the permissions from the subroles to the list of permissions
    public void addPermissions(String[] permissions) {
        for (String s : permissions) {
            this.permissions.append(s + ":");
        }
    }

    public String getPermissions() {
        return permissions.toString();
    }

}