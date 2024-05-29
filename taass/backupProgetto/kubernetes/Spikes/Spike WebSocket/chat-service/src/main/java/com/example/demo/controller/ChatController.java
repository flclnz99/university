package com.example.demo.controller;
import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatMessageRepository;
import com.example.demo.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;


@Controller
public class ChatController {

    @Autowired
    ChatMessageRepository repository;

    @MessageMapping("/chat/{room}")
    @SendTo("/topic/messages/{room}")
    public ChatMessage register(@DestinationVariable String room, @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        if (headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            System.out.println("User connected: " + chatMessage.getSender() + " on room: " + room);
        }

        // restituisco il messaggio di "CONNECT" cosicchè tutti i client possono
        // mostrare che un certo .getSender() si è connesso

        if(chatMessage.getType() == MessageType.CHAT){
            repository.save(chatMessage);
            System.out.println("MESSAGE SAVED" + chatMessage);
        }
        return chatMessage;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("SEND MESSAGE: " + chatMessage);
        return chatMessage;
    }

}