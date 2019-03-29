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
public class User {
    public RoleList roles;
    
    public User(Role r){
        roles = new RoleList(new Employee());
    }
    
    public void createNote(){
        if(roles.getPermissions().contains("create note")){
            System.out.println("created note!");
        } else {
            System.out.println("Do not have permission!");
        }
    }
}
