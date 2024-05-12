package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ChatMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatMessageApplication.class, args);
	}

}
