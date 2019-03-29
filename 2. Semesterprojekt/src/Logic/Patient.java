/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Patrick
 */
public class Patient extends Role {
    String[] permissions = {};
    
    public Patient() {
        super("Patient");
        super.addPermissions(permissions);
    }
    
}
