package client.controller;

import client.model.Client;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField labelUsername;

    @FXML
    private Button buttonGo;

    @FXML
    private Label labelError;

    private static Client model;
    private static final String HOST ="127.0.0.1";
    private static final int PORT = 4445;
    private BooleanProperty ready = new SimpleBooleanProperty();

    @FXML
    public void initialize() {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        labelUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldV, String newV) {
                if(newV != null && newV.equalsIgnoreCase("")){
                    labelError.setText("");
                }
            }
        });

        buttonGo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    if (inputValidation() && askIfExists()) {
                            ready.setValue(true);
                    }
            }
        });
    }

    public void initModel(Client model, BooleanProperty ready){
        this.model = model;
        this.ready = ready;
        labelUsername.textProperty().bindBidirectional(model.getEmailAddressProperty());
        model.getLoginErrMsgProperty().bindBidirectional(labelError.textProperty());
    }

    private boolean inputValidation(){
        if(!(labelUsername.getText().matches("[a-zA-Z0-9._%-]+(@gmail.com|@libero.it|@tiscali.it)"))){
            labelError.setText("Syntax error. Try again");
            //labelUsername.setText("");
            return false;
        }
        else {
            labelError.setText("");
            return true;
        }
    }

    public boolean askIfExists(){
        if (!model.askIfExists(HOST, PORT)) {
            labelError.setText("Sorry, address not found. Try again");
            //labelUsername.setText("");
            return false;
        } else {
            labelError.setText("");
            return true;
        }
    }

    public void clearUser() {
        labelUsername.setText("");
    }

}
