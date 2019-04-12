package Domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Victor & Sebber
 */
public class DiaryList {

    private ArrayList<DiaryNote> diaryList;
    private File diaryFile;
    private DiaryNote diaryNote;
    private String filename;
    private Date date;
    // private Employee currentEmployee;
    // private Patient currentPatient;

    public DiaryList() {
        diaryList = new ArrayList<>();
        //i stedet for notes skal der stå patientens navn så man kan finde notet tilhørende en person
        filename = "notes/" + convertDate() +/*currentEmployee.getName()+*/ ".txt";
        diaryFile = new File(filename);

        try {
            diaryFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);

        }
    }

    public void saveDiaryNote(DiaryNote diaryNote) {

        try (FileWriter fw = new FileWriter(diaryFile, true)) {
            fw.write(diaryNote.getNote());
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void removeDiaryNote(DiaryNote diary) {
        diaryList.remove(diary);
    }

    public void editDiaryNote(DiaryNote diary, String note) {
        diary.setNote(note);
    }

    public String convertDate() {
        date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String dateTime = dateFormat.format(date);
        return dateTime;
    }

    public String getNotes() {
        return diaryNote.getNote();
    }

}
