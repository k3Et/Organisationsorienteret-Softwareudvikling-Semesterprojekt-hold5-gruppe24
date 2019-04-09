/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.List;

/**
 *
 * @author Patrick
 */
public class User implements Comparable<User> {
    private String name;
    private String password;
    private String username;
    private String CPR;
    private String phoneNumber;
    private String email;
    private String address;
    
    private RoleList roles;

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
    
    public String getName(){
        return name;
    }
    
//    public User(Role r){
//        roles = new RoleList(r);
//    }
    
    public void createNote(){
        if(roles.getPermissions().contains("create note")){
            System.out.println("created note!");
        } else {
            System.out.println("Do not have permission!");
        }
    }
    
    public List getRoles(){
        return roles.getRoleList();
    }
    
    public RoleList getRoleList(){
        return roles;
    }
    
    @Override
   public String toString(){
       return name;
   }
   
     
   public String toStringAll(){
       return "Navn: "+ name+";"+"CPR: "+CPR+";"+"Mobil: "+phoneNumber+";"+"Email: "+email+";"+"Adresse: "+address+";"+"Rolle: "+roles+" ";
   }

    @Override
    public int compareTo(User o) {
        return getName().compareTo(o.getName());
    }
}
