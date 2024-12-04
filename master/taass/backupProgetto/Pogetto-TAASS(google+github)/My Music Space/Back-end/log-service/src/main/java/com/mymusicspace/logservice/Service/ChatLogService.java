package com.mymusicspace.logservice.Service;

import com.mymusicspace.logservice.model.LogChatroomData;
import com.mymusicspace.logservice.model.LogChatroomDataRepository;
import com.mymusicspace.logservice.model.RabbitMqChatData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ChatLogService {

    @Autowired
    private LogChatroomDataRepository repository;

    public ResponseEntity<ArrayList<RabbitMqChatData>> getTop10ChatroomNames() {
        try {
            ArrayList<LogChatroomData> topTenChats = (ArrayList<LogChatroomData>) repository.findTop10ByOrderByDateTimeDescUsersNumberDescMessagesNumberDesc();
            ArrayList<RabbitMqChatData> myData = new ArrayList<>();
            for(LogChatroomData d: topTenChats){
                myData.add(new RabbitMqChatData(d.getChatroom(), d.getMessagesNumber(), d.getUsersNumber()));
            }

            return new ResponseEntity<>(myData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> saveChatsLog(ArrayList<RabbitMqChatData> data) {
        System.out.println("Saving all data on DB...");
        try{
            ArrayList<LogChatroomData> data2 = new ArrayList<>();
            for(RabbitMqChatData d: data){
                data2.add(new LogChatroomData(d.getChatroom(), d.getMessagesNumber(), d.getUsersNumber(), new Date()));
            }
            repository.saveAll(data2);
            System.out.println("Data saved ...");
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
