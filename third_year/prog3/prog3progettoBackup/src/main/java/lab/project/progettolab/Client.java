package lab.project.progettolab;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe Client, conterrà la lista di mail che sarà il model
 */

public class Client {
    private static final int MAX_ATTEMPTS = 5;
    private final ListProperty<Email> inbox;
    private final ObservableList<Email> inboxContent;
    private final StringProperty emailAddress;
    private final Property<String> receivers;
    private final ArrayList<Email> pendingEmails;
    Socket socket = null;
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     * Costruttore della classe.
     *
     *
     */

    public Client() {
        this.inboxContent = FXCollections.observableList(new LinkedList<>());
        this.inbox = new SimpleListProperty<>();
        this.inbox.set(inboxContent);
        this.emailAddress = new SimpleStringProperty();
        this.receivers = new SimpleStringProperty();
        this.pendingEmails = new ArrayList<Email>();
    }

    /**
     * @return lista di email
     */
    public ListProperty<Email> inboxProperty() {
        return inbox;
    } /*###la vera lista di email è nell'inbox, la observable list InboxContent è solo un wrapper###*/

    /**
     * @return indirizzo email della casella postale
     */
    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    /**
     * @return indirizzi email destinatari
     */
    public Property<String> receivers() {
        return receivers;
    }

    /**
     * @return elimina l'email specificata
     */
    public void deleteEmail(Email email) {
        inboxContent.remove(email);
    }

    /**
     * communication with host
     */

    /*Effettua 5 tentativi di connessione al server. Esce dal loop in caso di connessione riuscita*/
    public void communicate(String host, int port) throws IOException {
        int attempts = 0;
        boolean success = false;

        while (attempts < MAX_ATTEMPTS && !success) {
            attempts += 1;
            //System.out.println("[Client " + this.emailAddressProperty().getValue() + "] Tentativo nr. " + attempts);
            success = tryCommunication(host, port);
            if (success) {
                continue;
            }
        }
    }/*###aggiungere gestione di errore di connessione? ###*/

    /*Tenta di comunicare con il server. Restituisce true se ha successo, false altrimenti*/
    private boolean tryCommunication(String host, int port) throws IOException {
        try {
            connectToServer(host, port);
            Integer index = inboxContent.size();
            String user = emailAddressProperty().getValue(); /*###mandarla come property? Come?###*/
            sendIndex(index);
            sendAddress(user);
            receiveData();
            if(!pendingEmails.isEmpty()) {
                sendEmails();
            }
            closeConnections();
            return true;
        } catch (ConnectException ce) {
            // nothing to be done
            return false;
        } catch (IOException | ClassNotFoundException | ParseException se) {
            se.printStackTrace();
            return false;
        }
       /*le connessioni verranno chiuse solo al momento del "log out"*/
    }

    private void sendIndex(Integer index) throws IOException {
        outputStream.writeObject(index);
        //outputStream.flush();
    }

    private void sendAddress(String address) throws IOException, ClassNotFoundException {
        outputStream.writeObject(address);
        outputStream.flush();
    }

    private void receiveData() throws IOException, ClassNotFoundException, ParseException {
        String myData = (String) inputStream.readObject();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(myData);
        JSONArray receivedEmails = (JSONArray) jsonObject.get("received");
        Email email = null;

        for (Object receivedEmail : receivedEmails) {
            JSONObject jsonEmail = (JSONObject) receivedEmail;
            JSONObject receivedEmail1 = (JSONObject) receivedEmail;

            List<String> receivers = getReceiversList(receivedEmail1);
            email = getEmailAttributes(jsonEmail, receivers);
                inboxContent.add(email);
        }

    }

    private List<String> getReceiversList(JSONObject jsonEmail) {
        JSONArray jsonReceivers = (JSONArray) jsonEmail.get("receivers");
        List<String> receivers = new ArrayList<String>();
        int i = 0;
        for(Object receiver : jsonReceivers){
            String rec = (String) jsonReceivers.get(i);
            receivers.add(0,rec);
        }
        return receivers;
    }
    private Email getEmailAttributes(JSONObject jsonEmail, List<String> receivers) {
        String subject = (String) jsonEmail.get("subject");
        String text = (String) jsonEmail.get("text");
        String datetime = (String) jsonEmail.get("datetime");
        String sender = (String) jsonEmail.get("sender");
        Email email = new Email(sender, receivers, subject, text, datetime);
        return email;
    }

    void getEmail(Email email) throws IOException {
        pendingEmails.add(email);
    }


    void sendEmails() {
            try {
                outputStream.writeObject(pendingEmails);
                outputStream.flush();
                 /*è meglio mettere il flush solo alla fine?*/
            } catch (ConnectException ce) {
                //nothing to be done
            } catch (IOException e) {
                e.printStackTrace();
            }
        /*le connessioni saranno chiuse solo al momento del "log out"*/
        //outputStream.reset();
    pendingEmails.clear();
    }
    /*###non funziona, perchè interferisce con la initialize del controller. come fare a essere certi che
    venga chiamata soltando quando il controller ha termintao initialize? ho provato a metterla dopo stage.show
    del firstscenecontroller e in molti altri punti ma non sembra funzionare###*/
    void receiveEmail() {
        while(true){
            Email email = null;
            try {
                email = (Email) inputStream.readObject(); /*###si può provare a usare stream diversi da objectstream, che siano bloccanti###*/
                //checkMailSent(email);
                System.out.println("client riceve mail");
                inboxContent.add(email);
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void connectToServer(String host, int port) throws IOException {
        socket = new Socket(host, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());

        // Dalla documentazione di ObjectOutputStream
        // callers may wish to flush the stream immediately to ensure that constructors for receiving
        // ObjectInputStreams will not block when reading the header.
        outputStream.flush();
        inputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("[Client " + "] Connesso");
    }

    void closeConnections() {
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

}