package com.bbmk.kafka.kafkaemail.service;

import com.bbmk.kafka.kafkaemail.kafka.KafkaEmailSender;
import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final KafkaEmailSender kafkaEmailSender;

    public NotificationService(KafkaEmailSender kafkaEmailSender) {
        this.kafkaEmailSender = kafkaEmailSender;
    }

    public void sendNotification(EmailRequest emailRequest) {
        kafkaEmailSender.sendEmailEvent(emailRequest);
    }
}
