package com.bbmk.kafka.kafkaemail.kafka.consumer;

import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import com.bbmk.kafka.kafkaemail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final MailService mailService;

    public EmailConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(topics = "email-topic", groupId = "my-group-id", containerFactory = "emailKafkaListenerContainerFactory")
    public void consumeEmailEvent(EmailRequest emailRequest) {
        mailService.sendSimpleMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
    }
}
