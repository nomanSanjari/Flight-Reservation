package com.proj.flightreservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/mail")
public class EmailController {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping()
    public void sendEmail(@RequestBody @NotNull JsonNode jsonNode) {
        String to = jsonNode.get("to").asText();
        String subject = jsonNode.get("subject").asText();
        String body = jsonNode.get("body").asText();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            //TO BE CHANGED LATER
            helper.setFrom("flightreservation999@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);  // Use true to enable HTML content

            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}
