package hello.hellospring.rapository;

import hello.hellospring.domain.ChatGroup;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatGroupRepository extends JpaRepository<ChatGroup, Long> {
    @Query("SELECT c FROM ChatGroup c INNER JOIN c.chatUser cu WHERE cu.user.id = :userId")
    List<ChatGroup> findChatRoomsByUserId(@Param("userId") Long userId);
}
