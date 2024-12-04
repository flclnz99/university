package client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Email implements Serializable {

    private String sender;
    private List<String> receivers;
    private String subject;
    private String text;
    private String datetime;
    private String id;

    public Email(String id, String sender, List<String> receivers, String subject, String text, String datetime) {
        this.id = id;
        this.sender = sender;
        this.subject = subject;
        this.text = text;
        this.receivers = new ArrayList<>(receivers);
        this.datetime = datetime;
    }

    public String getSender() {
        return sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getId() {
        return id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "From: " + sender +
                "  --   Re: " + subject +
                "  -- : " + datetime +
                "  --  preview: \""+text+"...\"";
    }
}


