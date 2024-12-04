package server.model;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddressExistsTask extends ServerTask implements Runnable {

    Integer index;
    ObservableList<String> logsContent;

    public AddressExistsTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream, String myClient, ObservableList<String> logsContent) {
        super(socket, inStream, outStream, myClient);
        this.logsContent = logsContent;
        this.index = index;

    }

    @Override
    public void run() {
        findAddress();
        closeStreams();
    }

    void findAddress(){
        Boolean success;
        try {
            if (checkIfExists(myClient)) {
                success = true;
                outStream.writeObject(success);
                outStream.flush();
            } else {
                success = false;
                outStream.writeObject(success);
                outStream.flush();
                String str = "Accesso negato a " + myClient + "; inesistente";
                Platform.runLater(() -> logsContent.add(str));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
