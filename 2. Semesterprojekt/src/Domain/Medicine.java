package Domain;

public class Medicine {

    private String name;
    private double dose;
    
    public Medicine(String name, double dose) {
        this.name = name;
        this.dose = dose;
    }

    public String getName() {
        return name;
    }

    public double getDose() {
        return dose;
    }

}
