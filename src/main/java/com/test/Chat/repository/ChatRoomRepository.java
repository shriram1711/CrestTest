package com.test.Chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Chat.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
