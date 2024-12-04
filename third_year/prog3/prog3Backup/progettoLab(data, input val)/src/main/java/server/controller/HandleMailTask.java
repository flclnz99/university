package server.controller;

import client.model.Email;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.List;

public class HandleMailTask implements Runnable{

    Socket socket = null;
    ObjectInputStream inStream = null;
    ObjectOutputStream outStream = null;
    String myClient = "";
    Email email;

    public HandleMailTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream, String myClient, Email email) {
        this.socket = socket;
        this.inStream = inStream;
        this.outStream = outStream;
        this.myClient = myClient;
        this.email = email;

    }
    @Override
    public void run() {
        readEmail();

        closeStreams();
    }

    private void readEmail() {
        try {
                checkMailSent(email);
        } catch (IOException e) {
            //disconnetto il socket
            closeStreams();
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("client disconnesso");
            System.out.println();
        } catch (ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void checkMailSent(Email email) throws IOException, ClassNotFoundException, ParseException {
        try {
            if(email != null) {
                System.out.println("servere legge: " + email.getSubject());
                JSONObject jsonMail = new JSONObject();
                jsonMail.put("sender", email.getSender());
                JSONArray receivers = new JSONArray();
                int i=0;
                for(String receiver: email.getReceivers()){
                    receivers.add(email.getReceivers().get(i));
                    i++;
                }
                /*###must be replaced with for each loop, see client code (getReceiversList())###*/
                jsonMail.put("id",email.getId());
                jsonMail.put("receivers", receivers);
                jsonMail.put("subject", email.getSubject());
                jsonMail.put("text", email.getText());
                jsonMail.put("datetime", email.getDatetime());
                int j=0;
                List<String> list = email.getReceivers();
                System.out.println("lista receivers:"+ list);
                while(j<=email.getReceivers().size()){
                    updateReceiverInbox(jsonMail, email.getReceivers().get(j));
                    System.out.println(email.getReceivers().get(j));
                    j++;
                }
                System.out.println("mando mail " + email.getSubject() + " a " + email.getReceivers());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /*###non dovrebbe ricevere lista di receivers ma una stringa. il loop lo fa checkEmailSent###*/
    private void updateReceiverInbox(JSONObject jsonMail, String receivers) throws IOException, ParseException {

        String pathname = "./src/main/resources/users/"+receivers+".json";
        RandomAccessFile randomAccessFile = new RandomAccessFile(pathname, "rw");

        long pos = randomAccessFile.length();
        while (randomAccessFile.length() > 0) {
            pos--;
            randomAccessFile.seek(pos);
            if (randomAccessFile.readByte() == ']') { /*basterebbe dirgli di scrivere in cima?!*/
                randomAccessFile.seek(pos);
                break;
            }
        }

        String jsonElement = prettyPrintJSON(jsonMail.toJSONString());
        randomAccessFile.writeBytes("," + jsonElement + "] }");

        randomAccessFile.close();
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
