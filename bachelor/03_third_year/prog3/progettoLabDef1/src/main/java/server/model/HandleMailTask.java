package server.model;

import client.model.Email;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class HandleMailTask extends ServerTask implements Runnable{

    Email email;
    ObservableList<String> logsContent;
    Lock writeLock;

    public HandleMailTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream,
                          String myClient, Email email, ObservableList<String> logsContent, Lock writeLock) {
        super(socket, inStream, outStream, myClient);
        this.email = email;
        this.logsContent = logsContent;
        this.writeLock = writeLock;
    }

    @Override
    public void run() {
        sendEmail(email);
        closeStreams();
    }

    public void sendEmail(Email email) {
        try {
            if (email != null) {
                JSONObject jsonMail = new JSONObject();
                jsonMail.put("sender", email.getSender());
                JSONArray receivers = new JSONArray();
                int i = 0;
                for (String receiver : email.getReceivers()) {
                    receivers.add(email.getReceivers().get(i));
                    i++;
                }
                jsonMail.put("id", email.getId());
                jsonMail.put("receivers", receivers);
                jsonMail.put("subject", email.getSubject());
                jsonMail.put("text", email.getText());
                jsonMail.put("datetime", email.getDatetime());
                int j = 0;
                List<String> list = email.getReceivers();
                boolean success = true;
                while (j < email.getReceivers().size()) {
                    String rec = email.getReceivers().get(j);
                    if (checkIfExists(rec)) {
                        updateReceiverInbox(jsonMail, rec);
                    } else {
                        success = false;
                        String logStr = "Error: client " + myClient + " tried to send email to non existing address";
                        Platform.runLater(() -> logsContent.add(logStr));
                    }
                    j++;
                }
                outStream.writeObject(success);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

        /*rimuovere parametro ridondante string receivers, c'è già in jsonMail*/
    private void updateReceiverInbox(JSONObject jsonMail, String receiver) throws IOException, ParseException {
        writeLock.lock();
        try {
            String pathname = "./src/main/resources/users/" + receiver + "/received.json";
            RandomAccessFile randomAccessFile = new RandomAccessFile(pathname, "rw");

            long pos = randomAccessFile.length(); //lenght of file
            while (randomAccessFile.length() > 0) { //find the last ]
                pos--;
                randomAccessFile.seek(pos);
                if (randomAccessFile.readByte() == ']') {
                    randomAccessFile.seek(pos); //save position right before last ] to write
                    break;
                }
            }

            String jsonElement = prettyPrintJSON(jsonMail.toJSONString());
            if (randomAccessFile.length() < 36) { //if no mails, add json object
                jsonElement = jsonElement + "]}"; //and re-write closing braces
            } else {
                jsonElement = "," + jsonElement + "]}"; //if there are mails already, comma is necessary
            }
            randomAccessFile.writeBytes(jsonElement);
            randomAccessFile.close();
        }
            finally{
            writeLock.unlock();
        }
        String logStr = "Il client " + receiver + " ha una nuova mail nella sua casella";
        Platform.runLater(() -> logsContent.add(logStr));
    }


}
