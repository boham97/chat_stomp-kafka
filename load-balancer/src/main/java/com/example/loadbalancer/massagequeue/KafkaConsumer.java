package com.example.loadbalancer.massagequeue;

import com.example.loadbalancer.dto.MemoryResDto;
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


    @KafkaListener(topics = "memory_response")
    public void memoryRequest(String jsonMessage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MemoryResDto memoryResDto = objectMapper.readValue(jsonMessage, MemoryResDto.class);
        //h2에 저장해야되요!
    }

}
