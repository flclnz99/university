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
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Integer.parseInt;

public class DeleteMailTask implements Runnable{

    Socket socket = null;
    ObjectInputStream inStream = null;
    ObjectOutputStream outStream = null;
    String myClient = "";
    Email email;

    public DeleteMailTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream, String myClient, Email email) {
        this.socket = socket;
        this.inStream = inStream;
        this.outStream = outStream;
        this.myClient = myClient;
        this.email = email;

    }

    @Override
    public void run() {
        try {
            delete(email);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        closeStreams();
    }

    private void delete(Email email) throws IOException, ParseException {
        String pathname = "./src/main/resources/users/"+myClient+".json";
        FileReader fileReader = new FileReader(pathname);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(fileReader);
        int size = json.size()/2;
        System.out.println(json.get(size)+"\n");

        JSONArray mails = (JSONArray) json.get("received");

        //while(!json.get(size).equals(email.getId())){
        //int jsonInt = (int) json.get(size);
        //int emailInt = parseInt(email.getId());
        //System.out.println(jsonInt+" jsonint\n");
        //System.out.println(emailInt+" emailInt\n");
            //f(){

            //}
        //}

        Iterator itr = mails.iterator();
        while (itr.hasNext()) {
            JSONObject obj = (JSONObject) itr.next();
            if (obj.get("id").equals(email.getId())) {
                itr.remove();
                System.out.println("removing "+obj.toString());
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

    }
    public static String prettyPrintJSON(String unformattedJsonString) {
        StringBuilder prettyJSONBuilder = new StringBuilder();
        int indentLevel = 0;
        boolean inQuote = false;
        for(char charFromUnformattedJson : unformattedJsonString.toCharArray()) {
            switch(charFromUnformattedJson) {
                case '"':
                    // switch the quoting status
                    inQuote = !inQuote;
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    break;
                case ' ':
                    // For space: ignore the space if it is not being quoted.
                    if(inQuote) {
                        prettyJSONBuilder.append(charFromUnformattedJson);
                    }
                    break;
                case '{':
                case '[':
                    // Starting a new block: increase the indent level
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    indentLevel++;
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    break;
                case '}':
                case ']':
                    // Ending a new block; decrese the indent level
                    indentLevel--;
                    appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    break;
                case ',':
                    // Ending a json item; create a new line after
                    prettyJSONBuilder.append(charFromUnformattedJson);
                    if(!inQuote) {
                        appendIndentedNewLine(indentLevel, prettyJSONBuilder);
                    }
                    break;
                default:
                    prettyJSONBuilder.append(charFromUnformattedJson);
            }
        }
        return prettyJSONBuilder.toString();
    }
    private static void appendIndentedNewLine(int indentLevel, StringBuilder stringBuilder) {
        stringBuilder.append("\n");
        for(int i = 0; i < indentLevel; i++) {
            // Assuming indention using 2 spaces
            stringBuilder.append("  ");
        }
    }


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
