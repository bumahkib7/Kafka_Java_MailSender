package com.bbmk.kafka.kafkaemail.kafka.producer;

import com.bbmk.kafka.kafkaemail.models.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, Order> orderKafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> orderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public void sendOrderEvent(Order order) {
        orderKafkaTemplate.send("order-topic", order);
    }
}
