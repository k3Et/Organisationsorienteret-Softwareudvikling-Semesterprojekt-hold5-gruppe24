package Domain;

import java.util.Date;

/**
 *
 * @author Benjamin
 */
public class DiaryNote {

    private String note;
    private String employee;
    private String time;
    private String editedTime;
    private Date date;
    private Medicine medicine;

    public DiaryNote(String note) {
        this.note = note;
        System.out.println("DNOTE " + note);
    }

    public String getNote() {
        return note;
    }

    public String getEmployee() {
        return employee;
    }

    public String getTime() {
        return time;
    }

    public String getEditedTime() {
        return editedTime;
    }

    public Date getDate() {
        return date;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setNote(String note) {
        this.note = note;
    }

}