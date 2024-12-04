package server.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class GetMailsTask extends ServerTask implements Runnable{

    Integer index;
    Lock readLock;

    public GetMailsTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream, String myClient, Integer index, Lock readLock) {
        super(socket, inStream, outStream, myClient);
        this.index = index;
        this.readLock = readLock;

    }
    @Override
    public void run() {
        welcomeClient();
        closeStreams();
    }

    void welcomeClient() {
        try {
                outStream.writeObject(getUserEmails(myClient, index));
                outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<JSONValue> getUserEmails(String address, Integer index) {
        readLock.lock();
        ArrayList<JSONValue> smallArray = new ArrayList<JSONValue>();
        try {
            String pathname = "./src/main/resources/users/" + address + "/received.json";
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(pathname));
            JSONArray receivedEmails = (JSONArray) jsonObject.get("received");
            List<JSONValue> smaller = receivedEmails.subList(index, receivedEmails.size());
            smallArray.addAll(smaller);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally{
            readLock.unlock();
        }
        return smallArray;
    }
}
