/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.DiaryNote;
import Domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Victor & Sebber
 */
public class DiaryList {

    private List<DiaryNote> diaryList;
    private File diaryFile;
    private DiaryNote diaryNote;
    private String filename;
    private Date date;
    private String patient;
    // private Employee currentEmployee;
    // private Patient currentPatient;

    public DiaryList() {
        diaryList = new ArrayList<>();

    }

    public void saveDiaryNote(DiaryNote diaryNote) {
        System.out.println("DATA " + diaryNote);
        //i stedet for notes skal der stå patientens navn så man kan finde notet tilhørende en person
        filename = "notes/" +"hjælp"+/* convertDate()*/ /*currentEmployee.getName()+*/ ".txt";
        diaryFile = new File(filename);

        try {
            diaryFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);

        }

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

    public void setPatientName(User patientName) {
        patient = patientName.toString();

    }

    public String getNotes() {
        return diaryNote.getNote();
    }

}
