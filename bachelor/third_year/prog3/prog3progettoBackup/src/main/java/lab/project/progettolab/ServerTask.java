package lab.project.progettolab;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerTask implements Runnable{

    Socket socket = null;
    ObjectInputStream inStream = null;
    ObjectOutputStream outStream = null;
    Map<String, JSONArray> clientList = new HashMap<String, JSONArray>();
    //Map<String, ServerTask> clientsMap;
    String myClient = "";
    ArrayList<Email> pendingEmails;
    boolean exit = false;

    public ServerTask(Socket socket) {
        this.socket = socket;
        pendingEmails = new ArrayList<Email>();
       // this.clientsMap = clientsMap;
    }

    @Override
    public void run() {
        try {
            openStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
        welcomeClient();
        System.out.println("client connesso.");

        System.out.println();

        //while(!exit) {
            waitForEmail();
        //}
        /*###gli streams verranno chiusi al momento della disconnessione del client. è giusto così? O
        * quando client fa log out, deve comunicarlo a server in modo che quest'ultimo possa chiamare
        * close streams? metodo facile ma un po' balordo potrebbe essere: quando client fa log out, manda
        * una mail con destinatario vuoto. questo in client.sendmail non viene consentito quindi non può
        * accadere per errore o per malignità dell'utente. ma è un indicatore per il server di chiudere
        * i proprio streams. oppure basta metterlo nel ciclo while? ----update: già provato nel ciclo while
        * di Server. non funziona###*/
    }

    // Inizializza il client con i suoi dati
    void welcomeClient() {
        try {
            Integer index = (Integer) inStream.readObject();
            String address = (String) inStream.readObject();
            if(checkIfExists(address)){
                myClient = address;
                clientList.put(address, getUserEmails(address, index)); /*###possibile utilizzo della concurrent hash map: controllare se il client è già registrato###*/
                outStream.writeObject(clientList.get(address).toString()); /*###ma json allora a che serve? Vedi getUserEmails###*/
                outStream.flush();
            } //else?
        } catch (IOException | ClassNotFoundException | ParseException e) {
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
            //System.out.println(i+" "+fileList[i]);
            //System.out.println(i+" "+address);
            //System.out.println(i+" "+success);
        }
        return success;
    }

    /*###visto che tanto dopo va fatto il to string, non avrebbe più senso mandare direttamente la stringa e farla
    parsificare al client? o forse sarebbe poco sicuro? perché?###*/
    private JSONArray getUserEmails(String address, Integer index) throws IOException, ParseException {
        String pathname = "./src/main/java/lab/project/progettolab/"+address+".json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(pathname)); //the location of the file
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray receivedEmails = (JSONArray) jsonObject.get("received");
        JSONArray smaller = (JSONArray) receivedEmails.subList(0+index, receivedEmails.size());
        return smaller;
    }

    private void waitForEmail() {
            try {
                pendingEmails = (ArrayList<Email>) inStream.readObject(); /*###si può provare a usare stream diversi da objectstream, che siano bloccanti###*/

                for(Email email : pendingEmails){
                checkMailSent(email);
                    }
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
                    //System.out.println(email.getReceivers().get(i)+"       duaiuauuauuauauau");
                    receivers.add(email.getReceivers().get(i));
                    i++;
                }
                 /*###must be replaced with for each loop, see client code (getReceiversList())###*/
                jsonMail.put("receivers", receivers);
                jsonMail.put("subject", email.getSubject());
                jsonMail.put("text", email.getText());
                jsonMail.put("datetime", email.getDatetime());
                int j=0;
                List<String> list = email.getReceivers();
                System.out.println("lista receivers:"+ list);
                while(j<=email.getReceivers().size()){
                    updateReceiverInbox(jsonMail, email.getReceivers().get(j));/*###va chiamato su ogni receiver con ciclo loop###*/
                    System.out.println(email.getReceivers().get(j)+"abc");
                   /* if(clientsMap.containsKey(email.getReceivers().get(j))){
                        System.out.println("sono il serverThread di "+ myClient + "chiamo il serverThread di "+ email.getReceivers().get(j));
                        clientsMap.get(email.getReceivers().get(j)).updateActiveReceiver(email);
                    }*/
                    j++;
                }
                System.out.println("mando mail " + email.getSubject() + " a " + email.getReceivers());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateActiveReceiver(Email email) {
        System.out.println("sono il serverThread di "+ myClient +" provo ad aggiornarlo");
        try {
            outStream.writeObject(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*###non dovrebbe ricevere lista di receivers ma una stringa. il loop lo fa checkEmailSent###*/
    private void updateReceiverInbox(JSONObject jsonMail, String receivers) throws IOException, ParseException {
        String pathname = "./src/main/java/lab/project/progettolab/"+receivers+".json";
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(pathname));
        JSONArray receivedMails = (JSONArray) jsonObject.get("received");
        receivedMails.add(jsonMail);
        jsonObject.put("received", receivedMails);
        /*
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); /*###si può prettify la stampa anche senza Gson###
        try {
            FileWriter fileWriter = new FileWriter(pathname);
            fileWriter.write(gson.toJson(jsonObject));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
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

    // apre gli stream necessari alla connessione corrente
    private void openStreams() throws IOException {
        System.out.println("Server Connesso");

        inStream = new ObjectInputStream(socket.getInputStream());
        outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.flush();
    }

}
