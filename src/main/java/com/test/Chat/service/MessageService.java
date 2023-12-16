package com.test.Chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Chat.model.Message;
import com.test.Chat.repository.MessageRepository;
import com.test.Chat.request.MessageRequest;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public Message sendMessage(MessageRequest messageRequest) {
		Message message = new Message();
		message.setMessage(messageRequest.getMessage());
		message.setSenderId(messageRequest.getSenderId());
		message.setReceiverId(messageRequest.getReceiverId());
		message.setChatRoomId(messageRequest.getChatRoomId());

		return messageRepository.save(message);
	}

	public List<Message> retrieveMessage(Long userId) {
		return messageRepository.findAllBySenderIdOrderByCreatedDateDesc(userId);
	}

}
