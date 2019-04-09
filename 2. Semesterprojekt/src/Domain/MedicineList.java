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
 
    private File medicineFile;
    private Medicine medicine;
    private ArrayList<Medicine> medicineList;
    
    public MedicineList(){
        medicineFile = new File("medicineList.txt");
    }
    
    public ArrayList getMedicineList(){
        medicineList = new ArrayList<>();
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
    
    public void addToMedicineList(Medicine medicine){
        try (Scanner sc = new Scanner(medicineFile)){
            while (sc.hasNext()) {
                String temp = sc.next();
                String[] tempArray = temp.split("[:]");
                if (tempArray[0].equals(medicine.getName())) {
                    System.out.println("Already exists");
                    return;
                }
            }
        try(FileWriter fw = new FileWriter(medicineFile,true)) {
                fw.write(medicine.getName()+":"+medicine.getDose()+"\n");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public void RemoveFromMedicineList(Medicine medicine){
        try (Scanner sc = new Scanner(medicineFile)){
            while (sc.hasNext()) {
                String temp = sc.next();
                String[] tempArray = temp.split("[:]");
                      
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
}
