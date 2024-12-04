package com.example.demo.model.chatLog;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RabbitMqChatData {
    private String chatroom;
    private long messagesNumber;
    private long usersNumber;
}
