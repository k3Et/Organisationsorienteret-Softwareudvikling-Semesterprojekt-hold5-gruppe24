/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Roles.Patient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class ListOfPatients {
    private List<User> list;
    
    public ListOfPatients(){
        list = new ArrayList<>();
    }
    
    public void addPatient(User u){
        for(Object r : u.getRoles()){
            if((Role)r instanceof Patient){
                list.add(u);
                return;
            }
        }
        Collections.sort(list);
    }
    
    public void removePatient(User u){
        if(list.contains(u)){
            list.remove(u);
        }
    }
}
