package com.bbmk.kafka.kafkaemail.kafka.consumer;

import com.bbmk.kafka.kafkaemail.models.Order;
import com.bbmk.kafka.kafkaemail.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final OrderService orderService;

    public OrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "order-topic", groupId = "my-group-id", containerFactory = "orderKafkaListenerContainerFactory")
    public void consumeOrderEvent(Order order) {
        orderService.processOrderEvent(order);
    }
}
