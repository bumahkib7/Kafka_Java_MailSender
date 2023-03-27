package com.bbmk.kafka.kafkaemail.kafka.producer;

import com.bbmk.kafka.kafkaemail.models.Orders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, Orders> orderKafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Orders> orderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public void sendOrderEvent(Orders order) {
        orderKafkaTemplate.send("order-topic", order);
    }
}
