package hello.hellospring.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {
    private long chatRoomId;
    private int type;
    private long userId;
    private String content;
    private LocalDateTime localDateTime;
}
