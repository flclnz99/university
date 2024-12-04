package server.model;

import client.model.Email;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.controller.GetMailsTask;
import server.controller.HandleMailTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 4445;
    private static final ArrayList<GetMailsTask> getMailsTasks = new ArrayList<>();
    private static final ExecutorService pool = Executors.newFixedThreadPool(2);
    private static final ListProperty<String> logs = new SimpleListProperty<>();;
    private static final ObservableList<String> logsContent = FXCollections.observableList(new LinkedList<>());

   public Server() {
        this.logs.set(logsContent);
    }

    public ListProperty<String> logsProperty() {
        return logs;
    }

    public void startListening() throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        while(true){
            Socket client = listener.accept();

            ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
            outStream.flush();

            String type ="";
            String clientName ="";
            Integer index = null;
            Email email = null;


            try {
                Request request = (Request) inStream.readObject();
                type = request.getRequestType();
                clientName = request.getClient();
                index = request.getIndex();
                email = request.getEmail();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if(type.equalsIgnoreCase("getMails")){
                GetMailsTask getMailsTask = new GetMailsTask(client, inStream, outStream, clientName, index);
                //getMailsTasks.add(getMailsTask);
                String str1 = "Il client " + clientName + " ha effettuato l'accesso";
                String str2 = "Il client " + clientName + " richiede un update";
                pool.submit(getMailsTask);
                if(index == 0) {
                    Platform.runLater(() -> logsContent.add(str1));
                }
                else {
                   Platform.runLater(() -> logsContent.add(str2));
                }
            }
            if(type.equalsIgnoreCase("sendMail")){
                HandleMailTask handleMailTask = new HandleMailTask(client, inStream, outStream, clientName, email);
                //getMailsTasks.add(getMailsTask);
                String str3 = "Il client " + clientName + " invia mail a "+ email.getReceivers();
                Platform.runLater(() -> logsContent.add(str3));
                pool.submit(handleMailTask);
            }

            if(type.equalsIgnoreCase("deleteMail")){
                DeleteMailTask deleteMailTask = new DeleteMailTask(client, inStream, outStream, clientName, email);
                //getMailsTasks.add(getMailsTask);
                String str4 = "Il client " + clientName + " elimina la mail "+ email.getId();
                Platform.runLater(() -> logsContent.add(str4));
                pool.submit(deleteMailTask);
            }
        }
    }


}