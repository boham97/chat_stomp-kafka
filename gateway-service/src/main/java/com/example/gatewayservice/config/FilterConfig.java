package com.example.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("chat-room",r -> r
                        .path("/chat-room/**")
                        .filters(f -> f.rewritePath("/chat-room/(?<segment>.*)", "/${segment}"))
                        .uri("lb://chat-message-service"))
                .route("load-balancer", r -> r
                        .path("/websocket-service/**")
                        .uri("lb://load-balancer")) // Weight 설정
                .build();
    }
}
