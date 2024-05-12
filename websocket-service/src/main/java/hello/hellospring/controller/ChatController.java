package hello.hellospring.controller;

import hello.hellospring.dto.ChatMessageDto;
import hello.hellospring.massagequeue.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final Environment env;
    private final KafkaProducer kafkaProducer;
    private final SimpMessageSendingOperations messagingTemplate;
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s",
                env.getProperty("local.server.port"));
    }
    @MessageMapping("/room/{roomId}")
    public void receiveRoom(@DestinationVariable Long roomId, ChatMessageDto message) {
        //카프카로 전송
        log.info("receiveRoom: " + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now());
        kafkaProducer.send("pub", message);
    }

    public void sendRoom(Long roomId, ChatMessageDto message){
        log.info("sendRoom: " + roomId + "/" + message.getUserId() + "/" + LocalDateTime.now());
        messagingTemplate.convertAndSend("/sub/room/" + roomId, message);
    }
}
