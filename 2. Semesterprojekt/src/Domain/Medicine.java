package Domain;

import java.util.ArrayList;

public class Medicine {

    private String name;
    private double dose;

    ArrayList<Medicine> medicineList = new ArrayList<>();

    public Medicine(String name, double dose) {
        this.name = name;
        this.dose = dose;
    }

    public void addMedicine(Medicine medicine) {
        medicineList.add(medicine);
    }
    
    public void removeMedicine(Medicine medicine) {
        
    }

    public ArrayList getMedicineList() {
        return medicineList;
    }

    public String getName() {
        return name;
    }

    public double getDose() {
        return dose;
    }

}
