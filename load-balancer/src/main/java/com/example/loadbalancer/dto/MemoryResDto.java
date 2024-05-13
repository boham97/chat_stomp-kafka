package com.example.loadbalancer.dto;

import lombok.Data;

@Data
public class MemoryResDto {
    private final String serviceName;
    private final long freeMemory;
}
