package hello.hellospring.dto;

import lombok.Data;

@Data
public class MemoryResDto {
    private final String serviceName;
    private final long freeMemory;
}
