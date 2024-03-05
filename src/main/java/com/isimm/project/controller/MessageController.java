package com.isimm.project.controller;

import com.isimm.project.classes.Message;
import com.isimm.project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/save")
    public Message sendMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @GetMapping("/bySenderId/{SenderId}")
    public List<Message> getMessages(@PathVariable("SenderId") String userid ) {
        return messageRepository.findBySenderIdOrReceiverId(userid);
    }
    @GetMapping("/all")
    public List<Message> all() {
        return messageRepository.findAll();
    }
}
