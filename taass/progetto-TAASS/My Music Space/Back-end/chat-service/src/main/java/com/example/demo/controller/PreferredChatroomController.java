package com.example.demo.controller;

import com.example.demo.model.preferredChat.PreferredChatroom;
import com.example.demo.service.PreferredChatroomService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat/preferred")
public class PreferredChatroomController {

    @Autowired
    private final PreferredChatroomService service;

    public PreferredChatroomController(RabbitTemplate rabbitTemplate, PreferredChatroomService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> getPreferredChatRoom(@RequestParam String userEmail) {
        return service.getPreferredChatRoom(userEmail);
    }

    @PostMapping(value = "/insert", produces = "application/json")
    public ResponseEntity<String> savePreferredChatRoom(@RequestBody PreferredChatroom chat){
        return service.savePreferredChatRoom(chat);
    }

    @DeleteMapping(value = "/leave", produces = "application/json")
    public ResponseEntity<String> deletePreferredChatRoom(@RequestBody PreferredChatroom chat){
        return service.deletePreferredChatRoom(chat);
    }
}
