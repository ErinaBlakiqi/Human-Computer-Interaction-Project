package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelpPopUpController implements Initializable {

    @FXML
    private Label labelHelpText;

    @FXML
    private Button btnToggleLanguage;

    private ResourceBundle bundle;
    private Locale currentLocale;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;
        this.currentLocale = bundle.getLocale();
        applyLanguage();
    }

    @FXML
    private void handleToggleLanguage() {
        if (currentLocale.getLanguage().equals("en")) {
            currentLocale = new Locale("sq", "AL");
        } else {
            currentLocale = new Locale("en", "US");
        }
        bundle = ResourceBundle.getBundle("help", currentLocale);
        applyLanguage();
    }

    private void applyLanguage() {
        labelHelpText.setText(bundle.getString("help_text"));
        btnToggleLanguage.setText(bundle.getString("toggle_language"));
    }

    public void setHelpText(String helpText) {
    }
}
