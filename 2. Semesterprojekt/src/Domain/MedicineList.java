package Domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Benjamin
 */
public class MedicineList {

    private File medicineFile;
    private Medicine medicine;
    private ArrayList<Medicine> lMedicine;

    public MedicineList() {
        medicineFile = new File("medicineList.txt");
    }

    public ArrayList getMedicineList() {
        lMedicine = new ArrayList<>();
        try (Scanner sc = new Scanner(medicineFile)) {
            while (sc.hasNext()) {
                String temp = sc.next();
                String[] tempArray = temp.split("[:]");
                lMedicine.add(new Medicine(tempArray[0], Double.parseDouble(tempArray[1])));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return lMedicine;
    }

    public void addToMedicineList(Medicine medicine) {
        try (Scanner sc = new Scanner(medicineFile)) {
            while (sc.hasNext()) {
                String temp = sc.next();
                String[] tempArray = temp.split("[:]");
                if (tempArray[0].equals(medicine.getName())) {
                    System.out.println("Already exists");
                    return;
                }
            }
            try (FileWriter fw = new FileWriter(medicineFile, true)) {
                fw.write(medicine.getName() + ":" + medicine.getDose() + "\n");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void RemoveFromMedicineList(Medicine medicine) {
        try (Scanner sc = new Scanner(medicineFile)) {
            while (sc.hasNext()) {
                String temp = sc.next();
                String[] tempArray = temp.split("[:]");

            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

}