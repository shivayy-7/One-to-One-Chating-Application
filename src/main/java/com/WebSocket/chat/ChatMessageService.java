package com.WebSocket.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.WebSocket.chatroom.ChatRoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ChatMessageService {
//
//	private final ChatMessageRepository chatMessageRepository;
//	private final ChatRoomService chatRoomService;
//	
//	
//	public ChatMessage save(ChatMessage chatMessage) {
//		try {
//			var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(),
//					chatMessage.getRecipientId(),
//					true).orElseThrow();
//			chatMessage.setChatId(chatId);
//			chatMessageRepository.save(chatMessage);
//		} catch (Exception e) {
//			log.error("Exception occurred in save()-> ChatMessageService "+ e);
//		}
//		return chatMessage;
//	}
//	
//	public List<ChatMessage> findChatMessages(String senderId, String recipientId){
//		try {
//			var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
//			return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
//		} catch (Exception e) {
//			log.error("Exception occurred in findChatMessages()-> ChatMessageService "+ e);
//		}
//		return new ArrayList<>();
//	}
//	
//}

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(); // You can create your own dedicated exception
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
