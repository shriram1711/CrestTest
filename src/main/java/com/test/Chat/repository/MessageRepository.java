package com.test.Chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findAllBySenderIdOrderByCreatedDateDesc(Long senderId);
	
}
