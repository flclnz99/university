package com.mymusicspace.logservice.rabbitmq;

import com.mymusicspace.logservice.Service.ChatLogService;
import com.mymusicspace.logservice.model.RabbitMqChatData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class MessageListener {

    @Autowired
    private ChatLogService service;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void rabbitListener(ArrayList<RabbitMqChatData> data) {
        System.out.println("receiving data from chat-service ...");
        this.service.saveChatsLog(data);
    }

}
