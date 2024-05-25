module com.example.shitjeblerjetonline {
    // Required modules
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Export the main application package
    exports application;
    // Open the main application package to javafx.fxml for dependency injection and reflection
    opens application to javafx.fxml;

    // Export and open the controllers package for JavaFX to access
    exports controllers;
    opens controllers to javafx.fxml;

    // Export and open the database package if needed
    exports database;
    opens database to javafx.fxml;

    // Export and open the model packages
    exports model;
    opens model to javafx.fxml;


    exports model.dto;
    opens model.dto to javafx.fxml;

    exports model.filter;
    opens model.filter to javafx.fxml;

    // Export and open the repository package if needed
    exports repository;
    opens repository to javafx.fxml;

    // Export and open the services package if needed
    exports services;
    opens services to javafx.fxml;

    // Export and open the utils package if needed
    exports utils;

    //opens processor to javafx.fxml;
    //opens resources to javafx.fxml;

    opens utils to javafx.fxml;

    // Ensure the resources folder is recognized by the module system
    opens com.example.shitjeblerjetonline to javafx.fxml;
    opens css to javafx.fxml;
    opens translations to javafx.fxml;
    opens views to javafx.fxml;
}
