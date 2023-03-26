package com.bbmk.kafka.kafkaemail.kafka;

import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailSender {

    private final KafkaTemplate<String, EmailRequest> emailKafkaTemplate;

    public KafkaEmailSender(KafkaTemplate<String, EmailRequest> emailKafkaTemplate) {
        this.emailKafkaTemplate = emailKafkaTemplate;
    }

    public void sendEmailEvent(EmailRequest emailRequest) {
        emailKafkaTemplate.send("email-topic", emailRequest);
    }
}
