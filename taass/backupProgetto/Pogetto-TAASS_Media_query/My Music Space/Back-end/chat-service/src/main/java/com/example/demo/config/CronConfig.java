package com.example.demo.config;

import com.example.demo.model.chatLog.RabbitMqChatData;
import com.example.demo.service.CronService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.demo.rabbitmq.RabbitMqConfig;

import java.util.*;


@Configuration
@EnableScheduling
public class CronConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CronService service;

    @Scheduled(cron = "0 0 0 L * *")
    public void scheduleFixedDelayTask() {
        ArrayList<RabbitMqChatData> chatDataList = service.findAllChatroomData(false);
        System.out.println("Data found ... ");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.ROUTING_KEY, chatDataList);
        System.out.println("Sending data to rabbitMQ Queue...");
    }
}
