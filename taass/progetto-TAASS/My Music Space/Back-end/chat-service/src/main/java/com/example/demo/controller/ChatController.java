package com.example.demo.controller;
import com.example.demo.model.chatMessage.ChatMessage;
import com.example.demo.model.chatMessage.MessageType;
import com.example.demo.service.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @MessageMapping("/chat/{room}")
    @SendTo("/topic/messages/{room}")
    public ChatMessage registerAndSend(@DestinationVariable String room, @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        if (headerAccessor.getSessionAttributes() != null) {

            // restituisco il messaggio di "CONNECT" cosicchè tutti i client possono mostrare che un certo .getSender() si è connesso
            if(chatMessage.getType() == MessageType.JOIN){
                headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
                System.out.println("User connected: " + chatMessage.getSender() + " on room: " + room);
            }else if (chatMessage.getType() == MessageType.CHAT){
                System.out.println("SEND MESSAGE: " + chatMessage);
                service.saveChat(chatMessage);
            }
        }
        return chatMessage;
    }
}