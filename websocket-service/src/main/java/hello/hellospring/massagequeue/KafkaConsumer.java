package hello.hellospring.massagequeue;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    // 배송준비된 주문의 준비시간 status  변경후 저장
    @KafkaListener(topics = "itemFinsih")
    public void orderFinish(String message) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        OrderRequestDto orderRequestDto = objectMapper.readValue(message, OrderRequestDto.class);
//        OrderEntity orderEntity = orderRepository.findById(orderRequestDto.getId()).orElseThrow(NoSuchElementException::new);
//
//        orderEntity.setFinishedTime(LocalDateTime.now().toString());
//        orderEntity.setStatus(1);
//        orderRepository.save(orderEntity);
    }


}
