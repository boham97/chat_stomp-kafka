package hello.hellospring.domain;

import hello.hellospring.Dto.ChatGroupDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@Entity
public class ChatGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatGroupId")
    private Long id;
    private String name;
    private int status;

    @OneToMany(mappedBy = "chatGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatUser> chatUserList;

    public ChatGroupDto toChatGroupDto(){
        return ChatGroupDto.builder()
                .id(id)
                .name(name)
                .userIdList(chatUserList.stream()
                        .map(ChatUser::getUser)
                        .map(User::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}