package server.model;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//contains methods shared among two or more task classes
public class ServerTask {

    Socket socket = null;
    ObjectInputStream inStream = null;
    ObjectOutputStream outStream = null;
    String myClient = "";

    public ServerTask(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream, String myClient) {
        this.socket = socket;
        this.inStream = inStream;
        this.outStream = outStream;
        this.myClient = myClient;
    }

    protected boolean checkIfExists(String address) throws IOException, ParseException {
        Boolean success=false;
        String username = address;
        File f = new File("./src/main/resources/users/"+username);
        if(f.isDirectory()) {
            success = true;
        }
        return success;
    }

    //For this method we referred to: https://stackoverflow.com/questions/4105795/pretty-print-json-in-java
    public static String prettyPrintJSON(String unformattedJsonString) {
        StringBuilder prettyJSONBuilder = new StringBuilder(); //append and insert
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


    // Closes streams used in last connection
    protected void closeStreams() {
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