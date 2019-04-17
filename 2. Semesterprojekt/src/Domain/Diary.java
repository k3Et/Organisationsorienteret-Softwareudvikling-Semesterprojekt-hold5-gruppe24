package Domain;

import Data.DiaryList;
import Domain.DiaryNote;
import java.io.File;

/**
 *
 * @author Victor & Sebber
 */
public class Diary {

    DiaryNote diary;
    //Denne klasse er udelukkende til for at hente og skrive data til DiaryList.
    DiaryList dList = new DiaryList();

    public void saveDiaryNote(DiaryNote diary) {
        System.out.println("DOMAIN " + diary);
        
        dList.saveDiaryNote(diary);
    }

    public void removeDiaryNote(DiaryNote diary) {
        this.diary = diary;
        dList.removeDiaryNote(diary);
    }

    public void editDiaryNote(DiaryNote diary, String note) {
        dList.editDiaryNote(diary, note);
    }

    public String convertDate() {
        return dList.convertDate();
    }

    public String getNotes() {
        return dList.getNotes();
    }

    public void setPatientName(User patientName) {
        dList.setPatientName(patientName);
    }
    
     public File[]  getFiles(){
         
          System.out.println("aaa");
        return dList.getFiles();
     }
}
