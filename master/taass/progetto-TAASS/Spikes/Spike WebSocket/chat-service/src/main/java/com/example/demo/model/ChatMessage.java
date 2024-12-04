package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@Entity
@Table
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private String sender;
    private MessageType type;
    private String room;
    private String date;


    public ChatMessage() {

    }

    public ChatMessage(Long id, String content, String sender, MessageType type, String room, String date) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.room = room;
        this.date = date;
    }

    public ChatMessage(String content, String sender, MessageType type, String room, String date) {
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.room = room;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", type=" + type +
                ", room='" + room + '\'' +
                ", date=" + date +
                '}';
    }
}
