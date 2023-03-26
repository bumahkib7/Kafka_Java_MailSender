package com.bbmk.kafka.kafkaemail.kafka.producer;

import com.bbmk.kafka.kafkaemail.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private final KafkaTemplate<String, User> userKafkaTemplate;

    public UserProducer(KafkaTemplate<String, User> userKafkaTemplate) {
        this.userKafkaTemplate = userKafkaTemplate;
    }

    public void sendUserEvent(User user) {
        userKafkaTemplate.send("user-topic", user);
    }
}
