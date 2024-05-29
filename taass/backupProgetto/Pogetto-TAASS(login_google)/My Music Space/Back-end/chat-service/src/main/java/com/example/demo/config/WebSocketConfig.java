package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/*
WHAT IS STOMP
Simple (or Streaming) Text Oriented Message Protocol (STOMP), formerly known as TTMP,
is a simple text-based protocol, designed for working with message-oriented middleware (MOM).
It provides an interoperable wire format that allows STOMP clients to talk with any message
broker supporting the protocol.

 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    //@CrossOrigin(origins = "http://localhost:5500")
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOrigins("http://localhost:5173").withSockJS().setClientLibraryUrl("https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js");
        //registry.addEndpoint("/websocket").withSockJS();
    }

    @Override
    //@CrossOrigin(origins = "http://localhost:5500")
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

    /*/

    - @EnableWebSocketMessageBroker annotation is used to enable our WebSocket server.

    - WebSocketMessageBrokerConfigurer interface is used to provide implementation
      for some of its methods to configure the websocket connection.

    - registerStompEndpoints method is used to register a websocket endpoint that
      the clients will use to connect to the server.

    - configureMessageBroker method is used to configure our message broker
      which will be used to route messages from one client to another.

     */

}
