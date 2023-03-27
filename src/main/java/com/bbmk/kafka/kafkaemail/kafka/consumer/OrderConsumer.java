package com.bbmk.kafka.kafkaemail.kafka.consumer;

import com.bbmk.kafka.kafkaemail.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final OrderService orderService;

    public OrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }


}
