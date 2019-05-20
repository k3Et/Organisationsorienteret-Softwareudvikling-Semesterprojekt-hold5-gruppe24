package GUI;

import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Patrick
 */
public class TableGetterSetter {

    private String name;

    private ChoiceBox<String> choiceBox;

    public TableGetterSetter(String name, ChoiceBox choiceBox) {
        this.name = name;

        this.choiceBox = choiceBox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    public void setChoiceBox(ChoiceBox choiceBox) {
        this.choiceBox = choiceBox;
    }

}
