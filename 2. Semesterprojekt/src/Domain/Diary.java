package Domain;

import Data.DiaryList;

/**
 *
 * @author Victor & Sebber
 */
public class Diary {

    //Denne klasse er udelukkende til for at hente og skrive data til DiaryList.
    DiaryList dList;

    public void saveDiaryNote(DiaryNote diaryNote) {
       System.out.println("DOMAIN " + diaryNote);
        dList.saveDiaryNote(diaryNote);
    }

    public void removeDiaryNote(DiaryNote diary) {
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

      public void setPatientName(User patientName){
          dList.setPatientName(patientName);
      }
}
