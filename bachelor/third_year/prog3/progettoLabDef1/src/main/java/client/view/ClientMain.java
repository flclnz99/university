package client.view;

import client.controller.ClientController;
import client.controller.LoginController;
import client.model.Client;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        StackPane root = new StackPane();
        Client model = new Client();
        BooleanProperty ready = new SimpleBooleanProperty();

        FXMLLoader loginFxmlLoader = new FXMLLoader(ClientMain.class.getResource("login.fxml"));
        BorderPane loginView = loginFxmlLoader.load();
        root.getChildren().add(loginView);
        loginView.toFront();
        LoginController loginController = loginFxmlLoader.getController();

        FXMLLoader clientFxmlLoader = new FXMLLoader(ClientMain.class.getResource("client.fxml"));
        FlowPane clientView = clientFxmlLoader.load();
        root.getChildren().add(clientView);
        clientView.toBack();
        ClientController clientController = clientFxmlLoader.getController();

        ready.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldV, Boolean newV) {
                if(newV == true){
                    loginView.toBack();
                    clientView.toFront();
                    clientController.communicate();
                }
                if(newV == false){
                    loginController.clearUser();
                    clientController.clearUser();
                    loginView.toFront();
                    clientView.toBack();
                }
            }
        });

        loginController.initModel(model, ready);
        clientController.initModel(model, ready);

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        /*System.out.println(ClientMain.class);
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("client.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ClientController clientController = fxmlLoader.getController();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username");
        String username = scan.nextLine();
        clientController.setUser(username);
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        clientController.communicate();*/
    }

    public static void main(String[] args) {
        launch();
    }
}