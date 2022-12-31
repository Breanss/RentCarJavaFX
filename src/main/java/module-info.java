module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.controller;
    opens com.example.demo1.controller to javafx.fxml;
    exports com.example.demo1.model;
    opens com.example.demo1.model to javafx.fxml;
    exports com.example.demo1.helper;
    opens com.example.demo1.helper to javafx.fxml;
}