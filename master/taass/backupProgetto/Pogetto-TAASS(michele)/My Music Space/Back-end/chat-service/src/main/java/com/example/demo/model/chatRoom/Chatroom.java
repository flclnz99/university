package com.example.demo.model.chatRoom;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatrooms")
public class Chatroom {
    @Id
    private String id;
    private String name;
    private String description;

    public Chatroom(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Chatroom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Chatroom(String name) {
        this.name = name;
    }

    public Chatroom() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}