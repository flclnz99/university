package client.controller;

import client.model.Client;
import client.model.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe Controller
 */

public class ClientController{
    @FXML
    private Label errMsg;

    @FXML
    private Label sender;

    @FXML
    private Label receivedSubject;

    @FXML
    private TextArea receivedContent;

    @FXML
    private Label receivedReceivers;

    @FXML
    private Label labelUsername;

    @FXML
    private TextArea txtEmailContent;

    @FXML
    private ListView<Email> EmailList;

    @FXML
    private SplitPane writeEmailPane;

    @FXML
    private SplitPane readEmailPane;

    @FXML
    private TextField EmailReceiver;

    @FXML
    private Label NewMessage;

    @FXML
    private TextField EmailSubject;

    @FXML
    private TextArea mailContent;

    @FXML
    private Button buttonExit;

    private static Client model;
    private Email selectedEmail;
    private Email emptyEmail;
    private static final String HOST ="127.0.0.1";
    private static final int PORT = 4445;

    @FXML
    public void initialize(){
        System.out.println("initialize");
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        //istanza nuovo client
        model = new Client();
        errMsg.setText("");
        labelUsername.textProperty().bindBidirectional(model.emailAddressProperty());
        selectedEmail = null;
        /*###codice del prof da decommentare quando si deciderà di
        implementare le funzioni di apertura delle mail###*/

        //binding tra EmailList e inboxProperty
        EmailList.itemsProperty().bindBidirectional(model.inboxProperty());
        EmailList.setOnMouseClicked(this::showSelectedEmail);  /*###idem come sopra###*/

        //binding tra EmailReceiver e receiver(lista destinatari email)
        EmailReceiver.textProperty().bindBidirectional(model.receivers());

        NewMessage.textProperty().bindBidirectional(model.newMessage());

        //emptyEmail = new Email("", List.of(""), "", ""); /*###idem come sopra###*/
        //updateDetailView(emptyEmail); /*###idem come sopra###*/
        System.out.println("fine initialize");

    }

    /*###non funziona, vedi stesso metodo nel model###*/

    public void transferMessage(String message) {
        //get username from firstScene
        labelUsername.setText(message);
    }
    protected void communicate() throws IOException {
        Thread th = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        model.communicate("127.0.0.1", 4445);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 20000);
        });
        //th.setDaemon(true);
        th.start();
    }


    private boolean inputValidation(){
        String receiverList=EmailReceiver.getText();
        String[] tmpSplit = receiverList.split(",");
        int i=0;
        while(i<tmpSplit.length) {
            if (!(tmpSplit[i].matches("[a-zA-Z0-9._%-]+(@gmail.com|@libero.it|@tiscali.it)"))) {
                System.out.println(tmpSplit[i]+"\n");
                errMsg.setText("Errore di sintassi. Riprovare");
                return false;
            } else {
                errMsg.setText("");
            }
            i++;
        }
        return true;
    }

    @FXML
    protected void openMailEditor(){
        errMsg.setText("");
        writeEmailPane.toFront();
    }
    @FXML
    public void editorToBack(MouseEvent mouseEvent) {
        errMsg.setText("");
        writeEmailPane.toBack();
    }

    @FXML
    public void closeMailEditor(MouseEvent mouseEvent) {
        writeEmailPane.toBack();
        EmailSubject.setText("");
        EmailReceiver.setText("");
        mailContent.setText("");
    }
    @FXML
    protected void openMail(){
        if(selectedEmail == null || sender.getText().equals("")){
            errMsg.setText("No email selected. Select mail to open");
        }
        else {
            errMsg.setText("");
            readEmailPane.toFront();
        }
    }
    @FXML
    protected void closeMailReader(){
        sender.setText("");
        receivedReceivers.setText("");
        receivedSubject.setText("");
        receivedContent.setText("");
        readEmailPane.toBack();
    }


    public void sendEmail(MouseEvent mouseEvent) throws IOException {
        emptyEmail = new Email("","", List.of(""), "", "", "");
        /*trasformare attributi in properties, binding tra elementi di writeEmailPane e attributi mail,
        * svuotare tutto una volta mandata. vedi TODOList*/
        emptyEmail.setSender(model.emailAddressProperty().getValue());
        List<String> receivers = new ArrayList<>();
        String tmp = EmailReceiver.getText();
        if(inputValidation()){
            String[] tmpSplit = tmp.split(",");
            int i=0;
            while(i<tmpSplit.length){
                receivers.add(tmpSplit[i]);
                i++;
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String datetime = dtf.format(now);
            String idDatetime = datetime.replace(".","").replace("-","").replace(":","");
            System.out.println(idDatetime);
            emptyEmail.setReceivers(receivers);
            emptyEmail.setSubject(EmailSubject.getText());
            emptyEmail.setDatetime(datetime);
            emptyEmail.setText(mailContent.getText());
            emptyEmail.setId(model.emailAddressProperty().getValue()+"%"+idDatetime);
            model.sendEmail(emptyEmail, HOST, PORT); /*###parametri uguali a quelli passati a communicate.
         è il caso di introdurre variabili globali?###*/
        }
    }

    public void disconnect(MouseEvent mouseEvent) {
        model.closeConnections(); /*###probabilmente si può applicare anche quando
        si chiude la finestra: sarebbe più sicuro. Come si fa?###*/
    }

    /*public void startListening() {
        Thread th = new Thread(() -> {
            model.receiveEmail();
        });
        th.setDaemon(true);
        th.start();
    }*/

    /*###codice del prof da decommentare quando si deciderà di implementare le funzioni
    di apertura e cancellazione delle mail###*/
    /**
     * Elimina la mail selezionata
     */
    @FXML
    protected void deleteMail() {
        model.deleteEmail(selectedEmail, HOST, PORT);
        updateDetailView(emptyEmail);
    }


    protected void showSelectedEmail(MouseEvent mouseEvent) {
        Email email = EmailList.getSelectionModel().getSelectedItem();

        selectedEmail = email;
        updateDetailView(email);
    }

    /**
     * Aggiorna la vista con la mail selezionata
     */
    protected void updateDetailView(Email email) {
        if(email != null) {
            sender.setText(email.getSender());
            receivedReceivers.setText(String.join(", ", email.getReceivers()));
            receivedSubject.setText(email.getSubject());
            receivedContent.setText(email.getText());
        }
    }

    public void reply(ActionEvent actionEvent) {
        if(sender.textProperty().equals("")){
            errMsg.setText("\"From\" field is empty. Go to mail editor or fill it yourself");
        }
        else{
            EmailReceiver.setText(sender.textProperty().getValue());
            openMailEditor();
        }
        closeMailReader();
    }


    public void replyAll(ActionEvent actionEvent) {
        if(sender.textProperty().equals("")){
            errMsg.setText("\"From\" field is empty. Go to mail editor or fill it yourself");
        }
        else{
            String allReceivers = sender.getText()+","+receivedReceivers.getText();
            EmailReceiver.setText(allReceivers);
            openMailEditor();
        }
        closeMailReader();

    }

    public void forward(ActionEvent actionEvent) {
        mailContent.setText(receivedContent.getText());
        EmailSubject.setText(receivedSubject.getText());
        openMailEditor();
    }
}
