package com.bbmk.kafka.kafkaemail.kafka;

import com.bbmk.kafka.kafkaemail.models.Orders;
import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, User> userKafkaTemplate;

    private final KafkaTemplate<String, Orders> orderKafkaTemplate;

    private final KafkaTemplate<String, EmailRequest> emailKafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, User> userKafkaTemplate,
                                KafkaTemplate<String, Orders> orderKafkaTemplate, KafkaTemplate<String, EmailRequest> emailKafkaTemplate) {
        this.userKafkaTemplate = userKafkaTemplate;
        this.orderKafkaTemplate = orderKafkaTemplate;
        this.emailKafkaTemplate = emailKafkaTemplate;
    }

    public void sendUser(User user, String topic) {
        userKafkaTemplate.send(topic, user);
    }

    public void sendOrder(Orders order, String topic) {
        orderKafkaTemplate.send(topic, order);
    }

    public void sendEmailRequest(EmailRequest emailRequest, String topic) {
        emailKafkaTemplate.send(topic, emailRequest);
    }
}
