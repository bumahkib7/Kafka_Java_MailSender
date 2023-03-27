package com.bbmk.kafka.kafkaemail.kafka.consumer;

import com.bbmk.kafka.kafkaemail.models.Orders;
import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user_topic", groupId = "my-group-id", containerFactory = "userKafkaListenerContainerFactory")
    public void consumeUser(User user) {
        // Process the consumed User object
        System.out.println("Consumed User: " + user);
    }

    @KafkaListener(topics = "order_topic", groupId = "my-group-id", containerFactory = "orderKafkaListenerContainerFactory")
    public void consumeOrder(Orders order) {
        // Process the consumed Order object
        System.out.println("Consumed Order: " + order);
    }

    @KafkaListener(topics = "email_request_topic", groupId = "my-group-id", containerFactory = "emailKafkaListenerContainerFactory")
    public void consumeEmailRequest(EmailRequest emailRequest) {
        // Process the consumed EmailRequest object
        System.out.println("Consumed EmailRequest: " + emailRequest);
    }
}
