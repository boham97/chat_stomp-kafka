package hello.hellospring.massagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring.config.ObjectMapperConfig;
import hello.hellospring.controller.ChatController;
import hello.hellospring.dto.ChatMessageDto;
import hello.hellospring.dto.MemoryResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ChatController controller;
    private final ObjectMapper objectMapper;
    private final Environment env;
    @KafkaListener(topics = "sub")
    public void receive(String jsonMessage){
        try {
            ChatMessageDto message = objectMapper.readValue(jsonMessage, ChatMessageDto.class);
            log.info("kafka receive: " + "sub" + "/" + "user" + "/" + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now());
            controller.sendRoom(message.getChatRoomId(), message);
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }
    @KafkaListener(topics = "memory_request")
    public void memoryRequest(){
        MemoryResDto memoryResDto = new MemoryResDto(env.getProperty("spring.application.name"), Runtime.getRuntime().freeMemory());
        try {
            String jsonMessage = objectMapper.writeValueAsString(memoryResDto);
            kafkaTemplate.send("memory_response", String.valueOf(Runtime.getRuntime().freeMemory()));
        }catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
