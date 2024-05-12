package hello.hellospring.massagequeue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hello.hellospring.controller.ChatController;
import hello.hellospring.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
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
    @KafkaListener(topics = "sub")
    public void receive(String jsonMessage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ChatMessageDto message = objectMapper.readValue(jsonMessage, ChatMessageDto.class);
        log.info("kafka receive: " + "sub" + "/" + "user" + "/" + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now());
        controller.sendRoom(message.getChatRoomId(), message);
    }
    @KafkaListener(topics = "memory_request")
    public void memoryRequest(String jsonMessage) throws IOException {
        kafkaTemplate.send("memory_response", String.valueOf(Runtime.getRuntime().freeMemory()));
    }

}
