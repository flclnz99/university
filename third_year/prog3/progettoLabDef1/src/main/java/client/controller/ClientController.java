package client.controller;

import client.model.Client;
import client.model.Email;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientController{
    @FXML
    private Label serverErrMsg;

    @FXML
    private Label mailErrMsg;

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
    private ListView<Email> emailList;

    @FXML
    private SplitPane writeEmailPane;

    @FXML
    private SplitPane readEmailPane;

    @FXML
    private TextField emailReceiver;

    @FXML
    private TextField emailSubject;

    @FXML
    private TextArea mailContent;

    @FXML
    private Button buttonExit;

    private static Client model;
    private Email selectedEmail;
    private Email emptyEmail;
    private static final String HOST ="127.0.0.1";
    private static final int PORT = 4445;
    private BooleanProperty ready = new SimpleBooleanProperty();
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @FXML
    public void initialize(){
        selectedEmail = null;
        emailList.setOnMouseClicked(this::showSelectedEmail);
    }

    public void initModel(Client model, BooleanProperty ready){
        this.model = model;
        this.ready = ready;

        emailReceiver.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldV, String newV) {
                if(newV != null && newV.equalsIgnoreCase("")){
                    mailErrMsg.setText("");
                    model.getErrMsgProperty().setValue("");
                }
            }
        });

        model.getErrMsgProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldV, String newV) {
                mailErrMsg.setText(newV);
            }
        });

        labelUsername.textProperty().bindBidirectional(model.getEmailAddressProperty());
        emailList.itemsProperty().bindBidirectional(model.getInboxProperty());
        emailReceiver.textProperty().bindBidirectional(model.getReceivers());
    }

    class CommunicateTask implements Runnable{
        @Override
        public void run() {
            Platform.runLater(() -> serverErrMsg.setText(""));
            try {
                model.communicate(HOST, PORT);
            }catch(SocketException se){
                Platform.runLater(() ->serverErrMsg.setText("Server error. Trying to reconnect..."));
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
    }

    public void communicate() {
        if(executorService.isShutdown()){
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        executorService.scheduleAtFixedRate(new CommunicateTask(), 0, 10, TimeUnit.SECONDS);
    }

    public void update(){
        try {
            model.communicate(HOST, PORT);
        }catch(SocketException se){
            Platform.runLater(() ->serverErrMsg.setText("Server error. Trying to reconnect..."));
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    private boolean inputValidation(){
        String receiverList = emailReceiver.getText().replaceAll("\\s+","");
        String[] tmpSplit = receiverList.split(",");
        int i=0;
        while(i<tmpSplit.length) {
            if (!(tmpSplit[i].matches("[a-zA-Z0-9._%-]+(@gmail.com|@libero.it|@tiscali.it)"))) {
                mailErrMsg.setText("Syntax error, try again");
                return false;
            } else {
                if(!model.getErrMsgProperty().getValue().equalsIgnoreCase("Email sent!")) {
                    model.getErrMsgProperty().setValue("");
                }
            }
            i++;
        }
        return true;
    }

    @FXML
    protected void openMailEditor(){
        model.getErrMsgProperty().setValue("");
        writeEmailPane.toFront();
    }

    @FXML
    public void editorToBack(MouseEvent mouseEvent) {
        model.getErrMsgProperty().setValue("");
        mailErrMsg.setText("");
        writeEmailPane.toBack();
    }

    @FXML
    public void closeMailEditor(MouseEvent mouseEvent) {
        model.getErrMsgProperty().setValue("");
        writeEmailPane.toBack();
        emailSubject.setText("");
        emailReceiver.setText("");
        mailContent.setText("");

    }
    @FXML
    protected void openMail(){
        if(selectedEmail == null || sender.getText().equals("")){
            mailErrMsg.setText("No email selected. Select mail to open");
        }
        else {
            model.getErrMsgProperty().setValue("");
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


    public void sendEmail(MouseEvent mouseEvent) {
        emptyEmail = new Email("","", List.of(""), "", "", "");
        emptyEmail.setSender(labelUsername.getText());
        List<String> receivers = new ArrayList<>();
        String tmp = emailReceiver.getText().replaceAll("\\s+","");
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
            emptyEmail.setReceivers(receivers);
            emptyEmail.setSubject(emailSubject.getText());
            emptyEmail.setDatetime(datetime);
            emptyEmail.setText(mailContent.getText());
            emptyEmail.setId(model.getEmailAddressProperty().getValue()+"%"+idDatetime);
            try {
                model.sendEmail(emptyEmail, HOST, PORT);
            } catch (IOException se) { //includes Socket Exception
                serverErrMsg.setText("Server error. Trying to reconnect...");
                model.addPendingEmail(emptyEmail);
            }
        }
    }

    public void disconnect(MouseEvent mouseEvent) throws IOException {
        model.askToBeDisconnected(HOST, PORT);
        executorService.shutdownNow();
        ready.setValue(false);
    }

    @FXML
    protected void deleteMail() {
        if(selectedEmail == null || sender.getText().equals("")){
            mailErrMsg.setText("No email selected. Select mail to delete");
        }
        else {
            try {
                model.deleteEmail(selectedEmail, HOST, PORT);
            } catch (IOException e) {
                serverErrMsg.setText("Server error. Trying to reconnect...");
            }
            updateDetailView(emptyEmail);
        }
    }

    protected void showSelectedEmail(MouseEvent mouseEvent) {
        Email email = emailList.getSelectionModel().getSelectedItem();
        selectedEmail = email;
        updateDetailView(email);
    }

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
            serverErrMsg.setText("\"From\" field is empty. Go to mail editor or fill it yourself");
        }
        else{
            emailReceiver.setText(sender.textProperty().getValue());
            openMailEditor();
        }
        closeMailReader();
    }


    public void replyAll(ActionEvent actionEvent) {
        if(sender.textProperty().equals("")){
            serverErrMsg.setText("\"From\" field is empty. Go to mail editor or fill it yourself");/*input validation catches error before you!*/
        }
        else{
            String allReceivers = sender.getText()+","+receivedReceivers.getText().replaceAll("\\s+","") ;
            emailReceiver.setText(allReceivers);
            openMailEditor();
        }
        closeMailReader();
    }

    public void forward(ActionEvent actionEvent) {
        mailContent.setText(receivedContent.getText());
        emailSubject.setText(receivedSubject.getText());
        openMailEditor();
    }

    public void clearUser() {
        model.clearData();
        serverErrMsg.setText("");
        mailErrMsg.setText("");
        sender.setText("");
        receivedSubject.setText("");
        receivedContent.setText("");
        receivedReceivers.setText("");
        emailReceiver.setText("");
        emailSubject.setText("");
        mailContent.setText("");
        emailList.toFront();

    }
}
