package com.example.demo.model.preferredChat;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PreferredChatroomRepository extends MongoRepository<PreferredChatroom, String> {

    List<PreferredChatroom> getPreferredChatRoomByUserEmail(String userEmail);
    Boolean existsByUserEmailAndAndChatRoom(String userEmail, String chatRoom);

    void deleteAllByUserEmailAndAndChatRoom(String userEmail, String chatRoom);
}
