package com.WebSocket.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class ChatController {
//
//	private final SimpMessagingTemplate messagingTemplate;
//	private final ChatMessageService chatMessageService;
//	
//	@MessageMapping("/chat")
//	public void processMessage(@Payload ChatMessage chatMessage) {
//		try {
//			ChatMessage savedMsg = chatMessageService.save(chatMessage);
//			messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
//					ChatNotification.builder()
//									.id(savedMsg.getId())
//									.senderId(savedMsg.getSenderId())
//									.recipientId(savedMsg.getRecipientId())
//									.content(savedMsg.getContent())
//									.build()
//					);
//		} catch (Exception e) {
//			log.error("Exception occurred in processMessage()-> ChatController "+ e);
//		}
//	}
//	
//	@GetMapping("/messages/{senderId}/{recipientId}")
//	public ResponseEntity<List<ChatMessage>> findChatMessages(
//			@PathVariable("senderId") String senderId,
//			@PathVariable("recipientId") String recipientId
//			){
//		try {
//			return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
//		} catch (Exception e) {
//			log.error("Exception occurred in findChatMessages()-> ChatController "+ e);
//		}
//		return ResponseEntity.badRequest().build();
//	}
//	
//}

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSenderId(),
                        savedMsg.getRecipientId(),
                        savedMsg.getContent()
                )
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
                                                 @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}
