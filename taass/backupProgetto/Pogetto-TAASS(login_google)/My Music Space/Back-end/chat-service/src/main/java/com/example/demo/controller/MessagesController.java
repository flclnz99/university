package com.example.demo.controller;
import com.example.demo.model.chatMessage.ChatMessage;
import com.example.demo.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/chat/messages")
public class MessagesController {

    private final ChatService service;

    public MessagesController(ChatService service) {
        this.service = service;
    }

    @GetMapping
    public List<ChatMessage> retrieveMessages(@RequestParam(name = "room") String room){
        return service.retrieveChatMessages(room);
    }

}
