package hello.hellospring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@RequiredArgsConstructor
@Controller
public class ChatRoomController {

	@PostMapping("/chat")
	public void create(){
	}
	@PostMapping("/change")
	public void change(){
	}
	@GetMapping("/chat")
	public void findAll(){
	}

	@PostMapping("/out/{id}")
	public void out(@PathVariable long id){
	}
	@PostMapping("/outAll/{id}")
	public void outAll(@PathVariable long id){
	}
	@PostMapping("/out/{chatId}/user/{userId}")
	public void create(@PathVariable long chatId, @PathVariable long userId){
	}
	@PostMapping("/invite/{chatId}")
	public void invite(@PathVariable long chatId){
	}
}
