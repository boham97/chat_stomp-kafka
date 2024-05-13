package com.example.loadbalancer.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("websocket-service1", r -> r
                        .weight("websocket", 1)
                        .filters(f -> f.rewritePath("/websocket-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://websocket-service-1"))
                .route("websocket-service2", r -> r
                        .weight("websocket", 1)
                        .filters(f -> f.rewritePath("/websocket-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://websocket-service-2"))
                .route("websocket-service3", r -> r
                        .weight("websocket", 1)
                        .filters(f -> f.rewritePath("/websocket-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://websocket-service-3"))
                .build();
    }
}
