package com.example.demo.config;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    // Ascoltiamo su questo evento, quando un client si disconnette
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

        StompHeaderAccessor header = StompHeaderAccessor.wrap(event.getMessage());
        if (header.getSessionAttributes() != null) {
            String username = (String) header.getSessionAttributes().get("username");
            if (username != null) {
                log.info("User disconnected: " + username);

                // Questo oggetto verrà recuperato dal client.
                // avendo settato LEAVE, il client verrà informato che
                // l'utente username ha lasciato la stanza
                var chatMessage = ChatMessage.builder()
                        .type(MessageType.LEAVE)
                        .sender(username)
                        .build();

                messageTemplate.convertAndSend("/topic/" + chatMessage.getRoom(), chatMessage);
            }
        }
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){

        StompHeaderAccessor header = StompHeaderAccessor.wrap(event.getMessage());
        if (header.getSessionAttributes() != null) {
            String username = (String) header.getSessionAttributes().get("username");
            if (username != null) {
                log.info("User connected: " + username);

                // Questo oggetto verrà recuperato dal client.
                // avendo settato LEAVE, il client verrà informato che
                // l'utente username ha lasciato la stanza
                var chatMessage = ChatMessage.builder()
                        .type(MessageType.JOIN)
                        .sender(username)
                        .build();

                messageTemplate.convertAndSend("/topic/" + chatMessage.getRoom(), chatMessage);
            }
        }
    }

}
