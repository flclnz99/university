module lab.project.progettolab {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;
    requires com.google.gson;


    opens lab.project.progettolab to javafx.fxml, com.google.gson;
    exports lab.project.progettolab;
}