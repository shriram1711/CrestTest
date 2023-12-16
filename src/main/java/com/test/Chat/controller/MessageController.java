package com.test.Chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Chat.model.Message;
import com.test.Chat.request.MessageRequest;
import com.test.Chat.service.MessageService;

@RestController
@RequestMapping("/messaging")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping("/send")
	public Message sendMessage(@RequestBody MessageRequest messageRequest) {
		return messageService.sendMessage(messageRequest);
	}

	@GetMapping("/retrieve/{userId}")
	public List<Message> retrieveMessage(@PathVariable Long userId) {
		return messageService.retrieveMessage(userId);
	}

}
