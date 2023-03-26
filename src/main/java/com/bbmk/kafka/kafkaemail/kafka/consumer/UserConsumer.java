package com.bbmk.kafka.kafkaemail.kafka.consumer;

import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    private final UserService userService;

    public UserConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "user-topic", groupId = "my-group-id", containerFactory = "userKafkaListenerContainerFactory")
    public void consumeUserEvent(User user) {
        userService.processUserEvent(user);
    }
}
