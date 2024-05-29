package com.mymusicspace.logservice.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogChatroomDataRepository extends MongoRepository<LogChatroomData, String> {

    //List<LogChatroomData> findTop10ByOrderByMessagesNumberDesc();
    List<LogChatroomData> findTop10ByOrderByDateTimeDescUsersNumberDescMessagesNumberDesc();
}
