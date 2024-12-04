package lab.project.progettolab;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ServerController {

    @FXML
    private ListView<String> logs;
    private static Server model;

    public void initialize(){
        model=new Server();

       logs.itemsProperty().bindBidirectional(model.logsProperty());
        startServer();
    }

    private void startServer() {
        Thread th = new Thread(() -> {

            try {
                model.startListening();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        th.setDaemon(true);
        th.start();
    }
}
