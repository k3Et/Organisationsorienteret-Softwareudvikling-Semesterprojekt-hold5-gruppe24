package Domain;

/**
 *
 * @author Benjamin
 */
public class DiaryNote {

    private String note;

    public DiaryNote(String note) {
        this.note = note;
        System.out.println("DNOTE " + note);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
