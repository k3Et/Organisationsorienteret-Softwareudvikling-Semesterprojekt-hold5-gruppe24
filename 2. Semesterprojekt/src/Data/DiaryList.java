/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.DiaryNote;
import Domain.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor & Sebber
 */
public class DiaryList {

    private List<DiaryNote> diaryList;
    private File diaryFile;
    private File diaryFolder;
    private DiaryNote diaryNote;
    private String filename;
    private String folderName;
    private Date date;
    private String patient;
    private List <File> fileList;
    private File [] fileArray ;
    // private Employee currentEmployee;
    // private Patient currentPatient;

    public DiaryList() {
        diaryList = new ArrayList<>();

    }

    public void saveDiaryNote(DiaryNote diaryNote) {
        System.out.println("DATA " + diaryNote);
        //i stedet for notes skal der stå patientens navn så man kan finde notet tilhørende en person
        folderName = "notes/" + patient + "/";  
        diaryFolder = new File(folderName);
        diaryFolder.mkdirs();  //dette laver en ny folder.
        //har delt de to op for ellers ville diaryFile lave folder hele vejen igennem path'en og ingen filer. Det vil sige at diaryFolder står kun for at lave folders.
        filename = "notes/" + patient + "/"+convertDate() + /*currentEmployee.getName()+*/ ".txt";
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
    
    public File[] getFiles(){
        fileList = new ArrayList<>();
        
         fileArray = new File(folderName).listFiles();
         String str = "";
         System.out.println("eee"+fileArray);
        
     
        return fileArray;
    }

}
