package hello.hellospring.massagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, ChatMessageDto message){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(message);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
        log.info("kafka send: " + topic + "/" + message.getChatRoomId() + "/" + message.getUserId() + "/" + LocalDateTime.now());

    }
}
