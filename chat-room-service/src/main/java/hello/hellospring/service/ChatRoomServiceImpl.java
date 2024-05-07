package hello.hellospring.service;

import hello.hellospring.Dto.ChatGroupDto;
import hello.hellospring.domain.ChatGroup;
import hello.hellospring.rapository.ChatGroupRepository;
import hello.hellospring.rapository.ChatUserRepository;
import hello.hellospring.rapository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final UserRepository userRepository;
    private final ChatUserRepository chatUserRepository;
    private final ChatGroupRepository chatGroupRepository;


    @Override
    public List<ChatGroupDto> findMyGroup(Long id) {
        return chatGroupRepository.findChatRoomsByUserId(id)
                .stream()
                .map(ChatGroup::toChatGroupDto)
                .collect(Collectors.toList());
    }
}
