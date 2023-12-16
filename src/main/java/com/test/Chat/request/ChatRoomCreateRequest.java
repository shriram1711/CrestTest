package com.test.Chat.request;

import java.util.List;

import com.test.Chat.model.User;

public class ChatRoomCreateRequest {

	private String name;

	private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
