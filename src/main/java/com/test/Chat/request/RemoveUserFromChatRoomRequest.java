package com.test.Chat.request;

public class RemoveUserFromChatRoomRequest {

	private Long userId;

	private Long chatRoomId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Long chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

}
