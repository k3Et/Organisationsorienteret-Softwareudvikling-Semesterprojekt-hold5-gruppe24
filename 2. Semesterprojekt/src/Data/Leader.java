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
public class Leader extends Role{
    private String[] permissions = {"create relation"};
    
    
    public Leader() {
        super("Leader");
        super.addPermissions(permissions);
    }
}
