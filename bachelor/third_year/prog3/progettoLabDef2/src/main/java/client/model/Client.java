package client.model;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import server.model.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Client {
    private final ListProperty<Email> inbox;
    private final ObservableList<Email> inboxContent;
    private final StringProperty emailAddress;
    private final StringProperty receivers;
    private final ArrayList<Email> pendingEmails;
    private final StringProperty errMsg;
    private final StringProperty loginError;
    Socket socket = null;
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    private int count = 0;


    public Client() {
        this.inboxContent = FXCollections.observableList(new LinkedList<>());
        this.inbox = new SimpleListProperty<>();
        this.inbox.set(inboxContent);
        this.emailAddress = new SimpleStringProperty();
        this.receivers = new SimpleStringProperty();
        this.pendingEmails = new ArrayList<Email>();
        this.errMsg = new SimpleStringProperty();
        this.loginError = new SimpleStringProperty();
    }

    public ListProperty<Email> getInboxProperty() {
        return inbox;
    }

    public StringProperty getEmailAddressProperty() {
        return emailAddress;
    }

    public StringProperty getErrMsgProperty() {
        return errMsg;
    }

    public StringProperty getLoginErrMsgProperty() {
        return loginError;
    }

    public Property<String> getReceivers() {
        return receivers;
    }

    public void addPendingEmail(Email pendingEmail){ pendingEmails.add(pendingEmail); }

    //Called by LoginController, asks server if inserted username exists and to access
    public boolean askIfExists(String host, int port) {
        boolean exists = false;
        try {
            connectToServer(host, port);
        } catch (IOException e) {
            loginError.setValue("Server down. Try again later");
        }
        Request request = new Request();
        request.setClient(getEmailAddressProperty().getValue());
        request.setRequestType("exists");
        try {
            outputStream.writeObject(request);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            exists = (Boolean) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            closeConnections();
        }
        return exists;
    }

    /*Attempts communication with server*/
    public void communicate(String host, int port) throws IOException {
        try {
            connectToServer(host, port);
            Request request = new Request();
            request.setIndex(inboxContent.size());
            request.setClient(getEmailAddressProperty().getValue());
            request.setRequestType("getMails");
            if(inboxContent.size() == 0){
                if(count != 0) {
                    request.setRequestType("Update");
                }
            }
            outputStream.writeObject(request);
            outputStream.flush();
            receiveData();
            if(!pendingEmails.isEmpty()) {
                for(Email pendingEmail : pendingEmails){
                    sendEmail(pendingEmail, host, port);
                }
            }
        } catch (ClassNotFoundException | ParseException se) {
            se.printStackTrace();
        }
        finally {
            count++;
        }
    }

    private void receiveData() throws IOException, ClassNotFoundException, ParseException {
        ArrayList<JSONValue> myData = (ArrayList<JSONValue>) inputStream.readObject();
        Email email = null;
        for (Object receivedEmail : myData) {
            JSONObject jsonEmail = (JSONObject) receivedEmail;
            email = getEmailAttributes(jsonEmail);
            Email finalEmail = email;
            Platform.runLater(() -> inboxContent.add(finalEmail));
        }
    }

    private Email getEmailAttributes(JSONObject jsonEmail) {
        List<String> receivers = getReceiversList(jsonEmail);
        String id = (String) jsonEmail.get("id");
        String subject = (String) jsonEmail.get("subject");
        String text = (String) jsonEmail.get("text");
        String datetime = (String) jsonEmail.get("datetime");
        String sender = (String) jsonEmail.get("sender");
        Email email = new Email(id, sender, receivers, subject, text, datetime);
        return email;
    }

    private List<String> getReceiversList(JSONObject jsonEmail) {
        JSONArray jsonReceivers = (JSONArray) jsonEmail.get("receivers");
        List<String> receivers = new ArrayList<String>();
        int i = 0;
        for(Object receiver : jsonReceivers){
            String rec = (String) jsonReceivers.get(i);
            receivers.add(i,rec);
            i++;
        }
        return receivers;
    }

    public void sendEmail(Email email, String host, int port) throws IOException {
        Request request = new Request();
        request.setClient(getEmailAddressProperty().getValue());
        request.setRequestType("sendMail");
        request.setEmail(email);

        connectToServer(host, port);
        outputStream.writeObject(request);
        outputStream.flush();

        String setLabel = "Email sent!";
        try {
            boolean foundRec = (boolean) inputStream.readObject();
            if(!foundRec){
                setLabel = "One or more user not found";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        errMsg.setValue(setLabel);
        if(pendingEmails.contains(email)) {
            pendingEmails.remove(email);
        }
    }

    public void deleteEmail(Email email, String host, int port) throws IOException {
        Request request = new Request();
        request.setClient(getEmailAddressProperty().getValue());
        request.setRequestType("deleteMail");
        request.setEmail(email);

        connectToServer(host, port);
        outputStream.writeObject(request);
        outputStream.flush();
        inboxContent.remove(email);
    }

    private void connectToServer(String host, int port) throws IOException {
        socket = new Socket(host, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.flush(); /*to avoid readers to be blocked*/
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void askToBeDisconnected(String host, int port) {
        if (socket != null) {
            try {
                connectToServer(host, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Request request = new Request();
            request.setClient(getEmailAddressProperty().getValue());
            request.setRequestType("disconnecting");
            try {
                outputStream.writeObject(request);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String serverResponse = "";
            try {
                serverResponse = (String) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(serverResponse.equalsIgnoreCase("Goodbye")) {
                try {
                    outputStream.writeObject("Goodbye");
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeConnections() {
        if (socket != null) {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearData(){
        this.inboxContent.clear();
        this.emailAddress.setValue("");
        this.receivers.setValue("");
        this.pendingEmails.clear();
        this.errMsg.setValue("");
    }

}