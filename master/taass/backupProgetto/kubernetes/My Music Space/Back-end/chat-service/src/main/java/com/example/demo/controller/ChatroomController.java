package com.example.demo.controller;

import com.example.demo.model.chatRoom.Chatroom;
import com.example.demo.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/chat/chatrooms")
public class ChatroomController {

    @Autowired
    private ChatroomService chatroomService;

    @GetMapping
    public List<Chatroom> getChatrooms(@RequestParam(required = false) String name,
                                       @RequestParam(defaultValue = "12") int maxChatrooms) {
        return chatroomService.getChatrooms(name, maxChatrooms);
    }

    @PostMapping
    public Chatroom createChatroom(@RequestBody Chatroom chatroom) {
        return chatroomService.createChatroom(chatroom);
    }

}