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
public class Controller {
    public static void main(String[] args) {
        ListOfUsers users = new ListOfUsers();
        User u = new User("Erik", "KnudErGud123", "Erik90", "1234567890", "12345678", "bla@bla.dk", "Amalienborg 1", new Employee());
        User u2 = new User("Birthe", "dfg", "LadyB", "0987654321", "88888888", "noget@bla.dk", "Tuberkulosevej 7", new Patient());
        User u3 = new User("Ib", "password", "username", "0258741369", "46137943", "ib@ib.dk", "Pinsevænget 80", new Admin());
        
        users.addUser(u);
        users.addUser(u2);
        users.addUser(u3);
        
        
    }
}
