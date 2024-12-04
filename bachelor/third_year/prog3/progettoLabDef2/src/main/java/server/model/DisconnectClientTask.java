package server.model;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DisconnectClientTask extends ServerTask implements Runnable {

    ObservableList<String> logsContent;

    public DisconnectClientTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream, String myClient, ObservableList<String> logsContent) {
        super(socket, inStream, outStream, myClient);
        this.logsContent = logsContent;
    }

    @Override
    public void run() {
        String bye = "Goodbye";
        try {
            outStream.writeObject(bye);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        closeStreams();
        String logStr = "Il client " + myClient + " è stato disconnesso";
        Platform.runLater(() -> logsContent.add(logStr));
    }

}