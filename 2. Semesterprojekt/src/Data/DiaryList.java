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
    private String aFolderName;
    private String standardPath;
    private Date date;
    private String resident;
    private List<File> fileList;
    private File[] fileArray;
    // private Employee currentEmployee;
    // private Patient currentPatient;
    private List<String> fileNameList;
    private Scanner s;
    private Scanner n;
    private File[] filesArray;

    public DiaryList() {
        diaryList = new ArrayList<>();

    }

    public void saveDiaryNote(DiaryNote diaryNote) {

//i stedet for notes skal der stå residentens navn så man kan finde notet tilhørende en person
        aFolderName = "notes/" + resident + "/";
        diaryFolder = new File(aFolderName);
        diaryFolder.mkdirs();  //dette laver en ny folder.
        //har delt de to op for ellers ville diaryFile lave folder hele vejen igennem path'en og ingen filer. Det vil sige at diaryFolder står kun for at lave folders.
        filename = "notes/" + resident + "/" + convertDate() + /*currentEmployee.getName()+*/ ".txt";
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
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        String dateTime = dateFormat.format(date);
        return dateTime;
    }

    public void setResidentName(User residentName) {
        resident = residentName.toString();

    }

    public String getNotes() {
        return diaryNote.getNote();
    }

    public List getFiles() {

        aFolderName = "notes/" + resident + "/";
        diaryFolder = new File(aFolderName);
        filesArray = diaryFolder.listFiles();
        List<String> sList = new ArrayList<>();
        for (File file : filesArray) {
            if (file.isFile()) {
                try {
                    s = new Scanner(file);
                    String tot = "";
                    while (s.hasNext()) {
                        tot += s.nextLine() + "\n";
                    }
                    sList.add(tot);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DiaryList.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    s.close();
                }
            }
        }
        return sList;
    }

    public List getFileName() {
        fileNameList = new ArrayList<>();
        String stringHolder;
        String fileNameFormatted;
        String date;
        String time;
        for (int i = 0; i < filesArray.length; i++) {

            stringHolder = filesArray[i].getName().replace(".txt", "").replace("_", " ");
            date = stringHolder.substring(0, 10).replace(" ", "/");
            time = stringHolder.substring(11, stringHolder.length()).replace(" ", ":");

            fileNameFormatted = date + " " + time;

            fileNameList.add(fileNameFormatted);

        }
        return fileNameList;

    }
}
