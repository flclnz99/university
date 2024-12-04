package com.example.demo.service;

import com.example.demo.model.chatLog.RabbitMqChatData;
import com.example.demo.model.chatMessage.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CronService {

    @Autowired
    private MongoTemplate template;

    public ArrayList<RabbitMqChatData> findAllChatroomData(Boolean requiredAll){

        ArrayList<RabbitMqChatData> data = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date endDate = calendar.getTime();

        // Parse the start and end dates to match the format of the stored dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        String startDateString = dateFormat.format(startDate);
        String endDateString = dateFormat.format(endDate);

        System.out.println(startDate);
        System.out.println(endDate);
        // Create the query to find documents within the current month
        Query query = new Query();
        query.addCriteria(Criteria.where("date").gte(startDateString).lt(endDateString));

        Set<String> collections = template.getCollectionNames();
        for (String collectionName : collections) {

            if(!collections.equals("chatrooms") && !collections.equals("preferredchatroom")) {
                RabbitMqChatData newData = new RabbitMqChatData();
                System.out.println("Processing collection: " + collectionName);
                ArrayList<ChatMessage> chats = new ArrayList<>();

                if(requiredAll){chats = (ArrayList<ChatMessage>) template.findAll(ChatMessage.class, collectionName);
                }else{chats = (ArrayList<ChatMessage>) template.find(query, ChatMessage.class, collectionName);}

                Set<String> uniqueSenders = new HashSet<String>();
                for (ChatMessage c : chats) {
                    uniqueSenders.add(c.getSender());
                }

                newData.setChatroom(collectionName);
                newData.setMessagesNumber(chats.size());
                newData.setUsersNumber(uniqueSenders.size());
                data.add(newData);
            }
        }
        return data;
    }
}
