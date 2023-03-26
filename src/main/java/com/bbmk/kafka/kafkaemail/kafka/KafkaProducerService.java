package com.bbmk.kafka.kafkaemail.kafka;

import com.bbmk.kafka.kafkaemail.models.Order;
import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, User> userKafkaTemplate;

    private final KafkaTemplate<String, Order> orderKafkaTemplate;

    private final KafkaTemplate<String, EmailRequest> emailKafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, User> userKafkaTemplate, KafkaTemplate<String, Order> orderKafkaTemplate, KafkaTemplate<String, EmailRequest> emailKafkaTemplate) {
        this.userKafkaTemplate = userKafkaTemplate;
        this.orderKafkaTemplate = orderKafkaTemplate;
        this.emailKafkaTemplate = emailKafkaTemplate;
    }

    public void sendUser(User user, String topic) {
        userKafkaTemplate.send(topic, user);
    }

    public void sendOrder(Order order, String topic) {
        orderKafkaTemplate.send(topic, order);
    }

    public void sendEmailRequest(EmailRequest emailRequest, String topic) {
        emailKafkaTemplate.send(topic, emailRequest);
    }
}
