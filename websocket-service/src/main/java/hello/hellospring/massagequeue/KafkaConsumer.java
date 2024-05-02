package hello.hellospring.massagequeue;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring.controller.ChatController;
import hello.hellospring.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ChatController controller;
//    @KafkaListener(topics = "${application.name}")
    @KafkaListener(topics = "ws-0")
    public void receive(String jsonMessage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessageDto message = objectMapper.readValue(jsonMessage, ChatMessageDto.class);
        log.info("kafka receive: " + "pub" + "/" + "user" + "/" + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now().toString());
        controller.sendRoom(message.getChatRoomId(), message);
    }


}
