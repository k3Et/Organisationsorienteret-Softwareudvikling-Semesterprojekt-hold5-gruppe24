/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Patrick
 */
public class Role {
    private String roleName;
    
    private StringBuilder permissions;
    
    public Role(){
        permissions = new StringBuilder();
    }
    
//    public Role(String roleName){
//        this.roleName = roleName;
//        permissions = new StringBuilder();
//    }
    
    public String getName(){
        return roleName;
    }
    
    public void addPermissions(String[] permissions){
        for(String s : permissions)
        this.permissions.append(s + ":");
    }
    
    public String getPermissions(){
        return permissions.toString();
    }
    
}
