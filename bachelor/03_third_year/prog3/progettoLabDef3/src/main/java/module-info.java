module lab.project.progettolab {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;


    exports client.model;
    opens client.model to com.google.gson, javafx.fxml;
    exports client.view;
    opens client.view to com.google.gson, javafx.fxml;
    exports client.controller;
    opens client.controller to com.google.gson, javafx.fxml;
    exports server.view;
    opens server.view to com.google.gson, javafx.fxml;
    exports server.model;
    opens server.model to com.google.gson, javafx.fxml;
    exports server.controller;
    opens server.controller to com.google.gson, javafx.fxml;
}