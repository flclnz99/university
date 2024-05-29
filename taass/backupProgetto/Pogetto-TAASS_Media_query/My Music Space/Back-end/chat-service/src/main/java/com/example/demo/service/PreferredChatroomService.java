package com.example.demo.service;

import com.example.demo.model.preferredChat.PreferredChatroom;
import com.example.demo.model.preferredChat.PreferredChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferredChatroomService {

    @Autowired
    private PreferredChatroomRepository repository;

    public List<String> getPreferredChatRoom(String userEmail) {
        List<PreferredChatroom> preferredChatrooms =  repository.getPreferredChatRoomByUserEmail(userEmail);
        ArrayList<String> chatrooms = new ArrayList<>();
        for(PreferredChatroom chat: preferredChatrooms){
            chatrooms.add(chat.getChatRoom());
        }
        return chatrooms;
    }

    public ResponseEntity<String> savePreferredChatRoom(PreferredChatroom chat) {
        if (chat == null || chat.getUserEmail() == null || chat.getChatRoom() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        PreferredChatroom saved = repository.save(chat);
        if(saved != null){
            System.out.println("Preferred chat " + chat.getChatRoom() + " for user " + chat.getUserEmail() + " added!");
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Invalid request data");
    }

    public ResponseEntity<String> deletePreferredChatRoom(PreferredChatroom chat) {
        if (chat == null || chat.getUserEmail() == null || chat.getChatRoom() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        if(repository.existsByUserEmailAndAndChatRoom(chat.getUserEmail(), chat.getChatRoom())){
            repository.deleteAllByUserEmailAndAndChatRoom(chat.getUserEmail(), chat.getChatRoom());
            System.out.println("Preferred chat " + chat.getChatRoom() + " for user " + chat.getUserEmail() + " deleted!");
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");

        }
    }
}
