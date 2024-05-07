package hello.hellospring.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@Entity
public class User {
    @Id
    @Column(name = "userId")
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChatUser> chatUserList;

}