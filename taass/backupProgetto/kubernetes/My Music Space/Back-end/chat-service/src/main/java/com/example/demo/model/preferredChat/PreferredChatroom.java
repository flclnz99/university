package com.example.demo.model.preferredChat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "preferredchatroom")
public class PreferredChatroom {

    @Id
    private String id;
    private String userEmail;
    private String chatRoom;

    public PreferredChatroom() {
    }

    public PreferredChatroom(String userEmail, String chatRoom) {
        this.userEmail = userEmail;
        this.chatRoom = chatRoom;
    }

    public PreferredChatroom(String id, String userEmail, String chatRoom) {
        this.id = id;
        this.userEmail = userEmail;
        this.chatRoom = chatRoom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Override
    public String toString() {
        return "PreferredChatRoom{" +
                "id='" + id + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", chatRoom='" + chatRoom + '\'' +
                '}';
    }
}
