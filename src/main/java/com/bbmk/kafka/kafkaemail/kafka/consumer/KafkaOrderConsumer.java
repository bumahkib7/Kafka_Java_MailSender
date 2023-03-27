package com.bbmk.kafka.kafkaemail.kafka.consumer;

import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.request.OrderRequest;
import com.bbmk.kafka.kafkaemail.service.OrderService;
import com.bbmk.kafka.kafkaemail.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderConsumer {
    private final UserService userService;
    private final OrderService orderService;

    public KafkaOrderConsumer(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.order}")
    public void processOrderEvent(OrderRequest orderRequest) {
        // Process order event received from Kafka
        User user = userService.getUserById(orderRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found")); // throw an exception if the Optional is empty
        orderService.createOrder(orderRequest, user);
        orderService.sendOrderConfirmationEmail(orderRequest);
    }

}
