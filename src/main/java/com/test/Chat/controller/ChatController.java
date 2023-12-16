package com.test.Chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Chat.model.ChatRoom;
import com.test.Chat.request.AddUserToChatRoomRequest;
import com.test.Chat.request.ChatRoomCreateRequest;
import com.test.Chat.request.RemoveUserFromChatRoomRequest;
import com.test.Chat.service.ChatRoomService;

@RestController
@RequestMapping("/chatroom")
public class ChatController {

	@Autowired
	private ChatRoomService chatRoomService;

	@PostMapping("")
	public ChatRoom createChatRoom(@RequestBody ChatRoomCreateRequest newChatRoom) {
		return chatRoomService.createChatRoom(newChatRoom);
	}

	@PostMapping("/user")
	public ChatRoom addUserIntoChatRoom(@RequestBody AddUserToChatRoomRequest addUserToChatRoomRequest) {
		return chatRoomService.addUserIntoChatRoom(addUserToChatRoomRequest);
	}

	@DeleteMapping("/user")
	public void removeUserFromTheChatRoom(@RequestBody RemoveUserFromChatRoomRequest removeUserFromChatRoomRequest) {
		chatRoomService.removeUserFromTheChatRoom(removeUserFromChatRoomRequest);
	}

}
