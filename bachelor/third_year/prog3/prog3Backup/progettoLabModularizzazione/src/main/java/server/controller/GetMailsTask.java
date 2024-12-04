package server.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GetMailsTask implements Runnable{

    Socket socket = null;
    ObjectInputStream inStream = null;
    ObjectOutputStream outStream = null;
    String myClient = "";
    Integer index;

    public GetMailsTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream, String myClient, Integer index) {
        this.socket = socket;
        this.inStream = inStream;
        this.outStream = outStream;
        this.myClient = myClient;
        this.index = index;

    }
    @Override
    public void run() {
        welcomeClient();
        System.out.println("client connesso.");
        closeStreams();
    }

    void welcomeClient() {
        try {
            if(checkIfExists(myClient)){
                outStream.writeObject(getUserEmails(myClient, index)); /*###ma json allora a che serve? Vedi getUserEmails###*/
                outStream.flush();
            } //else?
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfExists(String address) throws IOException, ParseException {
        Boolean success=false;
        String username = address+".json";
        File d = new File("./src/main/java/lab/project/progettolab");
        String fileList[] = d.list();
        for (int i=0;i<fileList.length;i++) {
            if(username.equals(fileList[i])) {
                success = true;
            }
        }
        return success;
    }

    /*###visto che tanto dopo va fatto il to string, non avrebbe più senso mandare direttamente la stringa e farla
    parsificare al client? o forse sarebbe poco sicuro? perché?###*/
    private ArrayList<JSONValue> getUserEmails(String address, Integer index) throws IOException, ParseException {
        /*si può migliorare per fare in modo di leggere solo part3e del file!
         * ma solo quando sarà precisato definitivamente l'aspetto del json*/
        String pathname = "./src/main/java/lab/project/progettolab/"+address+".json";
        JSONParser parser = new JSONParser(); /*è deprecato?!!!! D:*/
        Object obj = parser.parse(new FileReader(pathname)); //the location of the file
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray receivedEmails = (JSONArray) jsonObject.get("received");
        List<JSONValue> smaller = receivedEmails.subList(0+index, receivedEmails.size());
        ArrayList<JSONValue> smallArray = new ArrayList<JSONValue>();
        smallArray.addAll(smaller);
        return smallArray;
    }

    // Chiude gli stream utilizzati durante l'ultima connessione
    protected void closeStreams() {
        System.out.println("chiudo gli streams");
        try {
            if(inStream != null) {
                inStream.close();
            }
            if(outStream != null) {
                outStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
