package hello.hellospring.Dto;

import hello.hellospring.domain.ChatGroup;
import hello.hellospring.domain.ChatUser;
import hello.hellospring.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ChatGroupDto {
    private final Long id;
    private final String name;
    private final List<Long> userIdList;

}
