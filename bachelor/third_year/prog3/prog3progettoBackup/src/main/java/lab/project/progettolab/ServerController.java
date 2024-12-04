package lab.project.progettolab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServerController {
    @FXML
    private Label operationsLabel;
    private static Server model;

    public void initialize(){
        model=new Server();
        operationsLabel.setText("ciao");
    }
}
