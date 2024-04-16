module com.example.shitjeblerjetonline {


    requires com.dlsc.formsfx;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.sql;


    opens com.example.shitjeblerjetonline to javafx.fxml;
    opens application to javafx.fxml;
    opens controllers to javafx.fxml;
    opens database to javafx.fxml;
    opens model to javafx.fxml;
    opens processor to javafx.fxml;
    opens repository to javafx.fxml;
    opens resources to javafx.fxml;
    opens utilities to javafx.fxml;
    opens views to javafx.fxml;


    exports com.example.shitjeblerjetonline;
}