/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Yourk
 */
public class MedicineList {
 
    private ArrayList<Medicine> medicineList;
    private File medicineFile;
    private Medicine medicine;
    
    public MedicineList(){
        medicineList = new ArrayList<>();
        medicineFile = new File("medicineList.txt");
    }
    
    public void addMedicine(Medicine medicine){
        medicineList.add(medicine);
    }
    
    public void removeMedicine(Medicine medicine){
        medicineList.remove(medicine);
    }
    
    public ArrayList getMedicineList(){
        try(Scanner sc = new Scanner(medicineFile)){
            while (sc.hasNext()) {
                String temp = sc.next();
                String[] tempArray = temp.split("[:]");
                medicineList.add(new Medicine(tempArray[0], Double.parseDouble(tempArray[1])));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return medicineList;
    }
    
    public void saveMedicineList(){
        try(FileWriter fw = new FileWriter(medicineFile)) {
            for (Medicine medicine : medicineList){
                fw.write(medicine.getName()+":"+medicine.getDose()+"\n");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        medicineList.removeAll(medicineList);
    }
    
    
}
