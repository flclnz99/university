package com.example.demo.config;

import com.example.demo.model.chatMessage.ChatMessage;
import com.example.demo.model.chatMessage.MessageType;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    // Ascoltiamo su questo evento, quando un client si disconnette
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

        StompHeaderAccessor header = StompHeaderAccessor.wrap(event.getMessage());
        if (header.getSessionAttributes() != null) {
            String username = (String) header.getSessionAttributes().get("username");
            String room = (String) header.getSessionAttributes().get("room");
            if (username != null) {
                System.out.println("User disconnected: " + username);

                // Questo oggetto verrà recuperato dal client.
                // avendo settato LEAVE, il client verrà informato che
                // l'utente username ha lasciato la stanza
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setType(MessageType.LEAVE);
                chatMessage.setSender(username);
                chatMessage.setRoom(room);

                messageTemplate.convertAndSend("/topic/" + chatMessage.getRoom(), chatMessage);
            }
        }
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){

        StompHeaderAccessor header = StompHeaderAccessor.wrap(event.getMessage());
        if (header.getSessionAttributes() != null) {
            String username = (String) header.getSessionAttributes().get("username");
            String room = (String) header.getSessionAttributes().get("room");
            if (username != null) {
                System.out.println("User connected: " + username);

                // Questo oggetto verrà recuperato dal client.
                // avendo settato LEAVE, il client verrà informato che
                // l'utente username ha lasciato la stanza
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setType(MessageType.JOIN);
                chatMessage.setSender(username);
                chatMessage.setRoom(room);

                messageTemplate.convertAndSend("/topic/" + chatMessage.getRoom(), chatMessage);
            }
        }
    }

}
