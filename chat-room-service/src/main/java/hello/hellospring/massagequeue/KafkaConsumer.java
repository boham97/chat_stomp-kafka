package hello.hellospring.massagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    @KafkaListener(topics = "pub")
    public void receive(String jsonMessage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ChatMessageDto message = objectMapper.readValue(jsonMessage, ChatMessageDto.class);
        message.setLocalDateTime(LocalDateTime.now());
        log.info("kafka receive: " + "pub" + "/" + "user" + "/" + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now());

        String jsonInString = "";
        try {
            jsonInString = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send("sub", jsonInString);
    }


}
