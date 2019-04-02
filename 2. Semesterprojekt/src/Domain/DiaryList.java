package Domain;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Victor & Sebber
 */
public class DiaryList {
    
    private ArrayList<DiaryNote> diaryList;
    private File diaryFile;
    private DiaryNote diaryNote;
    private String filename;
    
    
    public DiaryList(int size){
        diaryList = new ArrayList<>();
        diaryFile = new File(filename);
    }
    
    public void addDiaryNote(DiaryNote diaryNote){
        diaryList.add(diaryNote);
    }
    
    public void removeDiaryNote(DiaryNote diary){
        diaryList.remove(diary);
    }
    
    public void editDiaryNote(DiaryNote diary, String note){
        diary.setNote(note);
    }
}
