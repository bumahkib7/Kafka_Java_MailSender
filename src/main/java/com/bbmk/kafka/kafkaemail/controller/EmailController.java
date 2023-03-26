package com.bbmk.kafka.kafkaemail.controller;

import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import com.bbmk.kafka.kafkaemail.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final NotificationService notificationService;

    public EmailController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        notificationService.sendNotification(emailRequest);
        return new ResponseEntity<>("Email sent successfully.", HttpStatus.OK);
    }
}
