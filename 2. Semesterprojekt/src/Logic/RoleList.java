/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class RoleList {
    private List<Role> roles;
    
    public RoleList(){
        roles = new ArrayList<>();
    }
    
    public RoleList(Role r){
        roles = new ArrayList<>();
        roles.add(r);
    }
    
    public void addRole(Role r){
        roles.add(r);
    }
    
    public void removeRole(String name){
        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).getName().equals(name)){
                roles.remove(i);
            }
        }
    }
    
    //Returns list where each index contains a string that is equal to a permission
    public List<String> getPermissions(){
        String permissions = "";
        for(Role r : roles){
            permissions += r.getPermissions();
        }
        String[] permissionsArray = permissions.split(":");
        List<String> list = new ArrayList<>();
        for(String s : permissionsArray){
            list.add(s);
        }
        return list;
    }
}
