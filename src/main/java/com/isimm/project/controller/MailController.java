package com.isimm.project.controller;

import com.isimm.project.repository.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {



    @Autowired
    private EmailSenderService senderService;
    @GetMapping("/send-email/{email}/{body}")

    public void triggerMail(@PathVariable("email") String email,@PathVariable("body") String body) throws MessagingException {
        senderService.sendSimpleEmail(email,
                " Isismm User Account ",
                body);

    }
    @GetMapping("/send-email/forgetpassword/{email}/{body}")

    public void fogetpass(@PathVariable("email") String email,@PathVariable("body") String body) throws MessagingException {
        senderService.sendSimpleEmail(email,
                " recuperer mot de pass ",
                body);

    }


    @GetMapping("/send-email-recuperer/{email}/{body}")

    public void Mail(@PathVariable("email") String email,@PathVariable("body") String body) throws MessagingException {
        senderService.sendSimpleEmail(email,
                "mutuelle CPG",
                "http://localhost:4200/recuperernew/"+body);

    }
}
