package client.controller;

import client.model.Client;
import client.model.Email;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
    private Label labelFrom;

    @FXML
    private Label labelTo;

    @FXML
    private Label labelSubject;

    @FXML
    private Label labelUsername;

    @FXML
    private TextArea txtEmailContent;

    @FXML
    private ListView<Email> EmailList;

    @FXML
    private SplitPane writeEmailPane;

    @FXML
    private TextField EmailReceiver;

    @FXML
    private TextField EmailSubject;

    @FXML
    private TextArea mailContent;

    @FXML
    private Button buttonExit;

    private static Client model;
    private Email selectedEmail;
    private Email emptyEmail;

    @FXML
    public void initialize(){
        System.out.println("initialize");
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        //istanza nuovo client
        model = new Client();
        labelUsername.textProperty().bindBidirectional(model.emailAddressProperty());
        // selectedEmail = null;
        /*###codice del prof da decommentare quando si deciderà di
        implementare le funzioni di apertura delle mail###*/

        //binding tra EmailList e inboxProperty
        EmailList.itemsProperty().bindBidirectional(model.inboxProperty());
        //EmailList.setOnMouseClicked(this::showSelectedEmail);  /*###idem come sopra###*/

        //binding tra EmailReceiver e receiver(lista destinatari email)
        EmailReceiver.textProperty().bindBidirectional(model.receivers());

        //emptyEmail = new Email("", List.of(""), "", ""); /*###idem come sopra###*/
        //updateDetailView(emptyEmail); /*###idem come sopra###*/
        System.out.println("fine initialize");

    }

    /*###non funziona, vedi stesso metodo nel model###*/

    public void transferMessage(String message) {
        //get username from firstScene
        labelUsername.setText(message);
    }
    public void communicate() throws IOException {
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

    @FXML
    protected void openMailEditor(){
        writeEmailPane.setStyle("visibility: visible;");
    }

    public void sendEmail(MouseEvent mouseEvent) throws IOException {
        /*input validation*/
        emptyEmail = new Email("", List.of(""), "", "", "");
        /*trasformare attributi in properties, binding tra elementi di writeEmailPane e attributi mail,
        * svuotare tutto una volta mandata. vedi TODOList*/
        emptyEmail.setSender(model.emailAddressProperty().getValue());
        List<String> receivers = new ArrayList<>();
        String tmp = EmailReceiver.getText();
        String[] tmpSplit = tmp.split(",");
        int i=0;
        while(i<tmpSplit.length){
            receivers.add(tmpSplit[i]);
            //System.out.println(tmpSplit[i]);
            i++;
        }
        System.out.println(receivers);
        emptyEmail.setReceivers(receivers);
        emptyEmail.setSubject(EmailSubject.getText());
        emptyEmail.setDatetime("23.03.21-04.12PM");
        emptyEmail.setText(mailContent.getText());
        model.sendEmail(emptyEmail, "127.0.0.1", 4445); /*###parametri uguali a quelli passati a communicate.
         è il caso di introdurre variabili globali?###*/

    }

    public void closeMailEditor(MouseEvent mouseEvent) {

        writeEmailPane.setStyle("visibility: hidden;");
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

     @FXML
     protected void onDeleteButtonClick() {
     model.deleteEmail(selectedEmail);
     updateDetailView(emptyEmail);
     }

     /**
      * Mostra la mail selezionata nella vista

     protected void showSelectedEmail(MouseEvent mouseEvent) {
     Email email = lstEmails.getSelectionModel().getSelectedItem();

     selectedEmail = email;
     updateDetailView(email);
     }

     /**
      * Aggiorna la vista con la mail selezionata

     protected void updateDetailView(Email email) {
     if(email != null) {
     lblFrom.setText(email.getSender());
     lblTo.setText(String.join(", ", email.getReceivers()));
     lblSubject.setText(email.getSubject());
     txtEmailContent.setText(email.getText());
     }
     }*/
}
