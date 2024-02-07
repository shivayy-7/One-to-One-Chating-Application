package com.WebSocket.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	@MessageMapping("/user.addUser")
	@SendTo("/user/public")
	public User addUser(@Payload User user) {
		
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			log.error("Exception occurred in addUser()-> UserController "+ e);
		}
		return user;
	}

	@MessageMapping("/user.disconnectUser")
	@SendTo("/user/public")
	public User disconnect(@Payload User user) {
		try {
			userService.disconnect(user);
		} catch (Exception e) {
			log.error("Exception occurred in disconnect()-> UserController "+ e);
		}
		return user;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findConnectedUsers(){
		try {
			return ResponseEntity.ok(userService.findConnectedUsers());
		} catch (Exception e) {
			log.error("Exception occurred in findConnectedUsers()-> UserController "+ e);
			return ResponseEntity.badRequest().build();
		}
	}

}
