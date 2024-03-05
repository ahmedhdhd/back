package com.isimm.project.repository;

import com.isimm.project.classes.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM Message m WHERE m.sender_id = :x OR m.receiver_id = :x", nativeQuery = true)
    List<Message> findBySenderIdOrReceiverId(@Param("x") String senderId);
}

