package server.model;

import client.model.Email;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {

    private static final int PORT = 4445;
    private static final ExecutorService pool = Executors.newFixedThreadPool(2);
    private static final ListProperty<String> logs = new SimpleListProperty<>();
    private static final ObservableList<String> logsContent = FXCollections.observableList(new LinkedList<>());

   public Server() {
       this.logs.set(logsContent);
    }

    public ListProperty<String> getLogsProperty() {
        return logs;
    }

    public void startListening() throws IOException {
        ReadWriteLock fullLock = new ReentrantReadWriteLock();
        Lock readLock = fullLock.readLock();
        Lock writeLock = fullLock.writeLock();

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
            if(type.equalsIgnoreCase("exists")){
                AddressExistsTask addressExistsTask = new AddressExistsTask (client, inStream, outStream, clientName, logsContent);
                String str0 = "Il client " + clientName + " richiede l'accesso";
                Platform.runLater(() -> logsContent.add(str0));
                pool.submit(addressExistsTask);

            }
            if(type.equalsIgnoreCase("getMails") || type.equalsIgnoreCase("Update")){
                GetMailsTask getMailsTask = new GetMailsTask(client, inStream, outStream, clientName, index, readLock);
                String str1 = "Il client " + clientName + " ha effettuato l'accesso";
                String str2 = "Il client " + clientName + " richiede un update";
                pool.submit(getMailsTask);
                if(index == 0 && !(type.equalsIgnoreCase("Update"))) {
                    Platform.runLater(() -> logsContent.add(str1));
                }
                else {
                   Platform.runLater(() -> logsContent.add(str2));
                }
            }
            else if(type.equalsIgnoreCase("sendMail")){
                HandleMailTask handleMailTask = new HandleMailTask(client, inStream, outStream, clientName, email, logsContent, writeLock);
                String str3 = "Il client " + clientName + " richiede di inviare mail a "+ email.getReceivers();
                Platform.runLater(() -> logsContent.add(str3));
                pool.submit(handleMailTask);
            }
            else if(type.equalsIgnoreCase("deleteMail")){
                DeleteMailTask deleteMailTask = new DeleteMailTask(client, inStream, outStream, clientName, email, writeLock);
                String str4 = "Il client " + clientName + "richiede di eliminare la mail "+ email.getId();
                Platform.runLater(() -> logsContent.add(str4));
                pool.submit(deleteMailTask);
            }
            else if(type.equalsIgnoreCase("disconnecting")){
                DisconnectClientTask disconnectClientTask = new DisconnectClientTask(client, inStream, outStream, clientName, logsContent);
                String str5 = "Il client " + clientName + " richiede di essere disconnesso";
                Platform.runLater(() -> logsContent.add(str5));
                pool.submit(disconnectClientTask);
            }
        }
    }
}