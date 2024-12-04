package com.mymusicspace.logservice.Controller;

import com.mymusicspace.logservice.Service.ChatLogService;
import com.mymusicspace.logservice.model.LogChatroomData;
import com.mymusicspace.logservice.model.RabbitMqChatData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("api/log/chat")
public class ChatLogController {

    private final ChatLogService service;

    public ChatLogController(ChatLogService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<ArrayList<RabbitMqChatData>> getTop10ChatroomNames(){
        return service.getTop10ChatroomNames();
    }


}
