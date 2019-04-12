package Domain;

import Domain.Roles.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Patrick
 */
public class Controller {

    ListOfUsers users = new ListOfUsers();
    List<Role> listOfRoles = new ArrayList<>();
    ListOfPatients listOfPatients = new ListOfPatients();
    ListOfEmployees listOfEmployees = new ListOfEmployees();

    Employee employee = new Employee();
    Admin admin = new Admin();
    Leader leader = new Leader();
    Patient patient = new Patient();

    public void setStockUsers() {
        User u = new User("Erik", "KnudErGud123", "Erik90", "1234567890", "12345678", "bla@bla.dk", "Amalienborg 1", employee);
        User u2 = new User("Birthe", "dfg", "LadyB", "0987654321", "88888888", "noget@bla.dk", "Tuberkulosevej 7", patient);
        User u3 = new User("Ib", "password", "username", "0258741369", "46137943", "ib@ib.dk", "Pinsevænget 80", admin);
        User u4 = new User("Leder John", "password", "username", "0258741369", "46137943", "ib@ib.dk", "Pinsevænget 80", leader);
        User test = new User("Victor", "test", "test", "452559 - 2451", "45878963", "email@gmail.com", "Addressevej 97", employee);

        users.addUser(u);
        users.addUser(u2);
        users.addUser(u3);
        users.addUser(u4);
        users.addUser(test);
        u.createUser(u2);
        u.createUser(test);
    }

    public List getStockUsers() {

        return users.getList();
    }

    public void setStockRoleList() {
        listOfRoles.add(employee);
        listOfRoles.add(patient);
        listOfRoles.add(leader);
        listOfRoles.add(admin);
    }

    public List getStockRoleList() {
        return listOfRoles;
    }

    public ListOfPatients getListOfPatients() {
        return listOfPatients;
    }

    public ListOfEmployees getListOfEmployees() {
        return listOfEmployees;
    }

}
