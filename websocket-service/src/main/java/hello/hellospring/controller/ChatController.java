package hello.hellospring.controller;

import hello.hellospring.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final Environment env;
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s",
                env.getProperty("local.server.port"));
    }
    @MessageMapping("/room/{roomId}")
    public ChatMessageDto receiveRoom(@DestinationVariable Long roomId, ChatMessageDto message) {
        //카프카로 전송
        log.info("message: " + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now().toString());
        return message;
    }
    @SendTo("/sub/room/{roomId}")
    public ChatMessageDto sendRoom(@DestinationVariable Long roomId, ChatMessageDto message){
        return message;
    }

    @MessageMapping("/user/{roomId}")
    public ChatMessageDto receiveUser(@DestinationVariable Long roomId, ChatMessageDto message) {
        //카프카로 전송
        log.info("message: " + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now().toString());
        return message;
    }
    @SendTo("/sub/user/{roomId}")
    public ChatMessageDto sendUser(@DestinationVariable Long roomId, ChatMessageDto message){
        return message;
    }
}
