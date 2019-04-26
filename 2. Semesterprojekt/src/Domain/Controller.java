package Domain;

import Domain.Roles.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class Controller {

    static ListOfUsers users = new ListOfUsers();
    static List<Role> listOfRoles = new ArrayList<>();

//    static Employee employee = new Employee();
//    static Admin admin = new Admin();
//    static Leader leader = new Leader();
//    static Resident resident = new Resident();
    public static void setStockUsers() {
        User u = new User("Erik", "KnudErGud123", "Erik90", "1234567890", "12345678", "bla@bla.dk", "Amalienborg 1", listOfRoles.get(0));
        User u2 = new User("Birthe Madsen", "dfg", "LadyB", "0987654321", "88888888", "noget@bla.dk", "Tuberkulosevej 7", listOfRoles.get(1));
        User u3 = new User("Ib", "password", "username", "0258741369", "46137943", "ib@ib.dk", "Pinsevænget 80", listOfRoles.get(3));
        User u4 = new User("Leder John", "password", "username", "0258741369", "46137943", "ib@ib.dk", "Pinsevænget 80", listOfRoles.get(2));
        User u5 = new User("Patrick Pilsby", "dfg", "LadyB", "0987654321", "88888888", "noget@bla.dk", "Tuberkulosevej 7", listOfRoles.get(1));
        User test = new User("Victor Elmkvist", "test", "test", "452559 - 2451", "45878963", "email@gmail.com", "Addressevej 97", listOfRoles.get(1));

        users.addUser(u);
        users.addUser(u2);
        users.addUser(u3);
        users.addUser(u4);
        users.addUser(u5);
        users.addUser(test);
        u.createUser(u2);
        u.createUser(test);

        ListOfEmployees.addEmployee(u);

        ListOfResidents.addResident(u2);
        ListOfResidents.addResident(u5);
        ListOfResidents.addResident(test);

    }

    public static List<User> getStockUsers() {

        return users.getList();
    }

    public static void setStockRoleList() {
        listOfRoles.add(new Employee());
        listOfRoles.add(new Resident());
        listOfRoles.add(new Leader());
        listOfRoles.add(new Admin());
    }

    public static List getStockRoleList() {
        return listOfRoles;
    }

    public static void addRoleToUser(User selectedUser, Role selectedRole) {
        if (!selectedUser.getRoles().contains(selectedRole)) {
            selectedUser.getRoleList().addRole(selectedRole);
            if (selectedRole instanceof Resident) {
                ListOfResidents.addResident(selectedUser);
            } else if (selectedRole instanceof Employee) {

                ListOfEmployees.addEmployee(selectedUser);

            }
        }
    }

    public static void removeRoleFromUser(User selectedUser, Role selectedRole) {
        if (selectedUser.getRoles().contains(selectedRole)) {
            selectedUser.getRoleList().removeRole(selectedRole);
            if (selectedRole instanceof Resident) {

                ListOfResidents.removeResident(selectedUser);
            } else if (selectedRole instanceof Employee) {
                ListOfEmployees.removeEmployee(selectedUser);

            }
        }
    }

}