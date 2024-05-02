package hello.hellospring.massagequeue;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring.controller.ChatController;
import hello.hellospring.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final Environment env;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ChatController controller;
    // 배송준비된 주문의 준비시간 status  변경후 저장
    @KafkaListener(topics = "#{env.getProperty('local.server.port') + 'user'}")   //SPel
    public void orderFinish(String jsonMessage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessageDto message = objectMapper.readValue(jsonMessage, ChatMessageDto.class);
        controller.receiveUser(message.getChatRoomId(), message);
    }


}
