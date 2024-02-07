package com.WebSocket.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

	List<ChatMessage> findByChatId(String s);
	
}
