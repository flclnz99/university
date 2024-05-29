package com.example.demo.config;

import com.example.demo.model.chatLog.RabbitMqChatData;
import com.example.demo.model.chatMessage.ChatMessage;
import com.example.demo.model.chatRoom.Chatroom;
import com.example.demo.rabbitmq.RabbitMqConfig;
import com.example.demo.service.ChatService;
import com.example.demo.service.ChatroomService;
import com.example.demo.service.CronService;
import com.google.gson.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.FileCopyUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Configuration
public class MongoDBConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ChatroomService chatroomService;

    @Autowired
    private ChatService service;

    @Autowired
    private CronService cronService;

    @Autowired
    ApplicationContext appContext;

    @Bean
    public int initChatDB() {

        Boolean init = false;
        String strJson = null;
        ClassPathResource classPathResource = new ClassPathResource("chatroom.json");
        try {
            byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            strJson = new String(binaryData, StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = gson.fromJson(strJson, JsonElement.class);
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            for (JsonElement element : jsonArray) {
                if (element.isJsonObject()) {
                    JsonObject jsonObject = element.getAsJsonObject();

                    String room = jsonObject.get("collection_name").getAsString();

                    if (!chatroomService.checkChatroomExists(room)) {
                        init = true;
                        chatroomService.createChatroom(new Chatroom(room));
                        JsonArray jsonMessages = jsonObject.getAsJsonArray("messages");
                        for (JsonElement messageElement : jsonMessages) {
                            if (messageElement.isJsonObject()) {
                                JsonObject messageObject = messageElement.getAsJsonObject();

                                String content = messageObject.get("content").getAsString();
                                String sender = messageObject.get("sender").getAsString();
                                String date = messageObject.get("date").getAsString();

                                service.saveChat(new ChatMessage(content, sender, room, date));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (init){
            System.out.println("DATI ------------------------------------------");
            System.out.println("DATI ------------------------------------------");
            System.out.println("DATI ------------------------------------------");
            System.out.println(init);
            System.out.println("DATI ------------------------------------------");
            System.out.println("DATI ------------------------------------------");
            System.out.println("DATI ------------------------------------------");

            ArrayList<RabbitMqChatData> chatDataList = cronService.findAllChatroomData(true);
            System.out.println("Data found ... ");
            rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.ROUTING_KEY, chatDataList);
            //rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.ROUTING_KEY, chatDataList);
            System.out.println("Sending data to rabbitMQ Queue...");
        }

        return 0;
    }
}
