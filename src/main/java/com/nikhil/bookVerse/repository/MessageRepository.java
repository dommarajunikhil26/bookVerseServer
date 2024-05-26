package com.nikhil.bookVerse.repository;

import com.nikhil.bookVerse.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
