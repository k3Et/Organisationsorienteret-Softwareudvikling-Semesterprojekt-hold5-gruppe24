package Domain;

import Data.Database;
import Data.DiaryList;
import java.util.List;

/**
 *
 * @author Victor & Sebber
 */
public class Diary {

    //Denne klasse er udelukkende til for at hente og skrive data til DiaryList.
    DiaryList dList = new DiaryList();
    Database database = new Database();

    public void saveDiaryNote(DiaryNote diary) {
        System.out.println("DOMAIN " + diary);

        dList.saveDiaryNote(diary);
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

    public void setResidentName(User residentName) {
        dList.setResidentName(residentName);
    }

    public List getFiles() {

        return dList.getFiles();
    }

    public List getFileName() {
        return dList.getFileName();
    }

}