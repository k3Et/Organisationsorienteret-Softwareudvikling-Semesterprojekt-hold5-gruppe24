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
public class Employee extends Role {
    private static String[] permissions = {"create note","view note","view patient"};
    
    
    public Employee() {
        super("Employee");
        super.addPermissions(permissions);
    }
    
    public String toString(){
        return "Employee";
    }
    
}
