package lab.project.progettolab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstSceneController {

    @FXML
    private TextField textAddress;

    @FXML
    private Button buttonGo;

    @FXML
    private Label labelSyntaxError;

    @FXML
    public void initialize() {
        //When button is clicked, load window and pass data
        buttonGo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(inputValidation()) { /*###va sostituito con if(inputValidation)##*/
                    loadSceneAndSendMessage(event);
                }
            }
        });
    }

    private boolean inputValidation(){
        if(!(textAddress.getText().matches("[a-zA-Z0-9._%-]+(@gmail.com|@libero.it|@tiscali.it)"))){
            labelSyntaxError.setText("Errore di sintassi. Riprovare");
            textAddress.setText("");
            return false;
        }
        else {
            labelSyntaxError.setText("");
            return true;
        }
    }

    private void loadSceneAndSendMessage(ActionEvent event) {
        try {
            //Load client scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
            Parent root = loader.load();
            //Get controller of client
            ClientController clientController = loader.getController();
            //Pass data to controller of client
            clientController.transferMessage(textAddress.getText());
            clientController.communicate();
            //Show client scene in same window
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 900, 600));
            stage.setTitle("User Inbox");
            stage.show();
            //clientController.startListening();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
