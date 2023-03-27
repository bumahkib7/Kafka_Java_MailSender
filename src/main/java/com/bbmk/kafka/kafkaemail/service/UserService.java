package com.bbmk.kafka.kafkaemail.service;

import com.bbmk.kafka.kafkaemail.kafka.producer.UserProducer;
import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.repository.UserRepository;
import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserProducer userProducer;

    private final NotificationService notificationService;

    public UserService(UserRepository userRepository, UserProducer userProducer, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
        this.notificationService = notificationService;
    }

    public User createUser(User userRequest) {
        User user = new User(userRequest.getName(), userRequest.getEmail());
        user = userRepository.save(user);
        userProducer.sendUserEvent(user);
        sendWelcomeEmail(user);
        return user;
    }

    private void sendWelcomeEmail(User user) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(user.getEmail());
        emailRequest.setSubject("Welcome to our platform");
        emailRequest.setText("Hello " + user.getName() + ",\n\nWelcome to our platform! We are happy to have you on board.\n\nBest regards,\nThe Team");
        notificationService.sendNotification(emailRequest);
    }

    public void processUserEvent(User user) {
        // Process user event received from Kafka
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
