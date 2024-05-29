package com.example.demo.model.chatRoom;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatroomRepository extends MongoRepository<Chatroom, String> {
    List<Chatroom> findFirst10ByNameContainingIgnoreCase(String name, int limit);
    boolean existsByName(String name);
}