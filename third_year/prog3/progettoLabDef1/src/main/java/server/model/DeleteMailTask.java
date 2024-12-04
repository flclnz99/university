package server.model;

import client.model.Email;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;

public class DeleteMailTask extends ServerTask implements Runnable {

    Email email;
    Lock readLock;
    Lock writeLock;

    public DeleteMailTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream,
                          String myClient, Email email, Lock writeLock) {
        super(socket, inStream, outStream, myClient);
        this.email = email;
        this.writeLock = writeLock;
    }

    @Override
    public void run() {
        removeEmail();
        closeStreams();
    }

    void removeEmail() {
        writeLock.lock();
        JSONObject json = null;
        String pathname = "./src/main/resources/users/" + myClient + "/received.json";
        try {
            FileReader fileReader = new FileReader(pathname);
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(fileReader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray mails = (JSONArray) json.get("received");
        Iterator itr = mails.iterator();
        while (itr.hasNext()) {
            JSONObject obj = (JSONObject) itr.next();
            if (obj.get("id").equals(email.getId())) {
                itr.remove();
            }
        }
        try {
        FileWriter fileWriter = new FileWriter(pathname);
        String jsonMailList = prettyPrintJSON(json.toJSONString());
            fileWriter.write(jsonMailList);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            writeLock.unlock();
        }
    }
}
