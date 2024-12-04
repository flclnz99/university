package com.example.demo.service;

import com.example.demo.model.chatMessage.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ChatService {

    @Autowired
    private MongoTemplate template;

    public void saveChat(ChatMessage chatMessage){
        template.save(chatMessage,chatMessage.getRoom());
        System.out.println("MESSAGE SAVED" + chatMessage + " --- On collection: " + chatMessage.getRoom());
    }

    public ArrayList<ChatMessage> retrieveChatMessages(String room){
        System.out.println("Retrieve 10 messages from room: " + room);
        Query query = new Query().limit(10);
        query.with(Sort.by(Sort.Order.desc("date")));
        return (ArrayList<ChatMessage>) template.find(query,ChatMessage.class, room);
    }
}
