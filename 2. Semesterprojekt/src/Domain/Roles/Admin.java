/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Roles;

import Domain.Role;

/**
 *
 * @author Patrick
 */
public class Admin extends Role {
    private String[] permissions = {};
    
    public Admin() {
        super("Admin");
        super.addPermissions(permissions);
    }
    public String toString(){
        return "Admin";
    }
}
