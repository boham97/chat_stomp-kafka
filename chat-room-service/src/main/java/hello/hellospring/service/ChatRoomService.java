package hello.hellospring.service;

import hello.hellospring.Dto.ChatGroupDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    public List<ChatGroupDto> findMyGroup(Long id);
}
