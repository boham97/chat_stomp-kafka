package com.example.loadbalancer.massagequeue;

import com.example.loadbalancer.dto.MemoryResDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final RouteDefinitionRepository routeDefinitionRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "memory_response")
    public void memoryRequest(String jsonMessage) throws IOException {
        MemoryResDto memoryResDto = objectMapper.readValue(jsonMessage, MemoryResDto.class);
        String routeId = memoryResDto.getServiceName();
        long weight = memoryResDto.getFreeMemory();
        log.info(memoryResDto.toString());
        routeDefinitionRepository.getRouteDefinitions()
                .filter(routeDefinition -> routeDefinition.getId().equals(routeId))
                .flatMap(routeDefinition -> {
                    routeDefinition.getFilters().stream()
                            .filter(filterDefinition -> filterDefinition.getName().equals("Weight"))
                            .findFirst()
                            .ifPresent(filterDefinition -> {
                                // Update weight value
                                filterDefinition.getArgs().put("websocket", String.valueOf(weight));
                            });
                    return routeDefinitionRepository.save(Mono.just(routeDefinition));
                })
                .subscribe();
    }

}
