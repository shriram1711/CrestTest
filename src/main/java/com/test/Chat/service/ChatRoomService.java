package com.test.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Chat.model.ChatRoom;
import com.test.Chat.model.User;
import com.test.Chat.repository.ChatRoomRepository;
import com.test.Chat.repository.UserRepository;
import com.test.Chat.request.AddUserToChatRoomRequest;
import com.test.Chat.request.ChatRoomCreateRequest;
import com.test.Chat.request.RemoveUserFromChatRoomRequest;

@Service
public class ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@Autowired
	private UserRepository userRepository;

	public ChatRoom createChatRoom(ChatRoomCreateRequest newChatRoom) {

		ChatRoom cr = new ChatRoom();

		cr.setName(newChatRoom.getName());
		cr.setUsers(newChatRoom.getUsers());

		return chatRoomRepository.save(cr);
	}

	public ChatRoom addUserIntoChatRoom(AddUserToChatRoomRequest addUserToChatRoomRequest) {

		Long userId = addUserToChatRoomRequest.getUserId();
		Long chatRoomId = addUserToChatRoomRequest.getChatRoomId();

		User user = userRepository.findById(userId).orElse(null);
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElse(null);

		if (user != null && chatRoom != null) {
			chatRoom.getUsers().add(user);
			user.getChatRooms().add(chatRoom);

			chatRoomRepository.save(chatRoom);
		}

		return null;
	}

	public void removeUserFromTheChatRoom(RemoveUserFromChatRoomRequest removeUserFromChatRoomRequest) {

		Long userId = removeUserFromChatRoomRequest.getUserId();
		Long chatRoomId = removeUserFromChatRoomRequest.getChatRoomId();

		User user = userRepository.findById(userId).orElse(null);
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElse(null);

		if (user != null && chatRoom != null) {
			chatRoom.getUsers().remove(user);
			user.getChatRooms().remove(chatRoom);

			chatRoomRepository.save(chatRoom);
		}

	}

}
