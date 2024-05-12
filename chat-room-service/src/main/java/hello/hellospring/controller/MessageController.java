package hello.hellospring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MessageController {
    private final Environment env;
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s",
                env.getProperty("local.server.port"));
    }
}
