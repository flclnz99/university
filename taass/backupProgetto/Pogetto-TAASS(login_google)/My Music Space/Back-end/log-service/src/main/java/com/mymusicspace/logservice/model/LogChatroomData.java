package com.mymusicspace.logservice.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document
public class LogChatroomData {
    @Id
    private String id;
    private String chatroom;
    private long messagesNumber;
    private long usersNumber;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateTime;

    public LogChatroomData() {
    }

    public LogChatroomData(String id, String chatroom, long messagesNumber, long usersNumber, Date dateTime) {
        this.id = id;
        this.chatroom = chatroom;
        this.messagesNumber = messagesNumber;
        this.usersNumber = usersNumber;
        this.dateTime = dateTime;
    }

    public LogChatroomData(String chatroom, long messagesNumber, long usersNumber) {
        this.chatroom = chatroom;
        this.messagesNumber = messagesNumber;
        this.usersNumber = usersNumber;
    }

    public LogChatroomData(String chatroom, long messagesNumber, long usersNumber, Date dateTime) {
        this.chatroom = chatroom;
        this.messagesNumber = messagesNumber;
        this.usersNumber = usersNumber;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatroom() {
        return chatroom;
    }

    public void setChatroom(String chatroom) {
        this.chatroom = chatroom;
    }

    public long getMessagesNumber() {
        return messagesNumber;
    }

    public void setMessagesNumber(long messagesNumber) {
        this.messagesNumber = messagesNumber;
    }

    public long getUsersNumber() {
        return usersNumber;
    }

    public void setUsersNumber(long usersNumber) {
        this.usersNumber = usersNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "LogChatroomData{" +
                "id='" + id + '\'' +
                ", chatroom='" + chatroom + '\'' +
                ", messagesNumber=" + messagesNumber +
                ", usersNumber=" + usersNumber +
                ", dateTime=" + dateTime +
                '}';
    }
}
