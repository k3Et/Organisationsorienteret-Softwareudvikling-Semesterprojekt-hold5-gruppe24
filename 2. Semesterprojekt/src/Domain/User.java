package Domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Patrick
 */
public class User {

    private String name;
    private String password;
    private String username;
    private String CPR;
    private String phoneNumber;
    private String email;
    private String address;

    public RoleList roles;

    public User(String name, String password, String username, String CPR, String phoneNumber, String email, String address, Role r) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.CPR = CPR;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        roles = new RoleList();
    }

//    public User(Role r){
//        roles = new RoleList(r);
//    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void createNote() {
        if (roles.getPermissions().contains("create note")) {
            System.out.println("created note!");
        } else {
            System.out.println("Do not have permission!");
        }
    }


    public String createUser(User u) {
        if (roles.getPermissions().contains("create user")) {
            File file = new File("src/Data/LoginData.txt");
            try (Scanner scan = new Scanner(file); FileWriter fw = new FileWriter(file, true);) {
                String uName = u.getUsername();
                String pWord = u.getPassword();
                String tot = "";
                while (scan.hasNext()) {
                    tot += scan.nextLine();
                }
                String[] data = tot.split(";");
                for (int i = 0; i < data.length; i += 2) {
                    if (data[i].equals(uName)) {
                        return "User already exists";
                    }
                }
                fw.append(uName + ";" + pWord + ";\n");
                return "User created successfully";
            } catch (IOException ex) {
                System.out.println(ex.getStackTrace());
                return "Exception: IOException";
            }
        }
        return "Not permitted";
    }
}

    
    public RoleList getRole(){
        return roles;
    }
    
    @Override
   public String toString(){
       return name;
   }
   
     
   public String toStringAll(){
       return "Navn: "+ name+";"+"CPR: "+CPR+";"+"Mobil: "+phoneNumber+";"+"Email: "+email+";"+"Adresse: "+address+";"+"Rolle: "+roles+" ";
   }
}
