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
public class Controller {
    public static void main(String[] args) {
        User u = new User(new Employee());
        
        u.createNote();
    }
}
