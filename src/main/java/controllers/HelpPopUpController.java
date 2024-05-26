package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HelpPopUpController {

    @FXML
    private TextArea helpTextArea;

    public void setHelpText(String helpText) {
        helpTextArea.setText(helpText);
    }
}
