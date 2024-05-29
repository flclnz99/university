package com.example.demo.service;

import com.example.demo.model.chatRoom.Chatroom;
import com.example.demo.model.chatRoom.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ChatroomService {

    @Autowired
    private ChatroomRepository chatroomRepository;

    public List<Chatroom> getChatrooms(String name, int maxChatrooms) {


        if (name != null && !name.isEmpty()) {
            return chatroomRepository.findFirst10ByNameContainingIgnoreCase(name, maxChatrooms);
        } else {

            // Return 10 random chatrooms if no name provided
            // Fetch 10 chatrooms starting from the random index
            long count = chatroomRepository.count();
            Random random = new Random();
            int randomIndex = random.nextInt((int) (count/maxChatrooms));
            return chatroomRepository.findAll(PageRequest.of(randomIndex, maxChatrooms)).getContent();
        }
    }

    public Boolean checkChatroomExists(String name) {
        return chatroomRepository.existsByName(name);
    }

    public Chatroom createChatroom(Chatroom chatroom) {
        return chatroomRepository.save(chatroom);
    }

}
