package com.bbmk.kafka.kafkaemail.controller;

import com.bbmk.kafka.kafkaemail.models.Orders;
import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.request.OrderRequest;
import com.bbmk.kafka.kafkaemail.service.OrderService;
import com.bbmk.kafka.kafkaemail.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }


    @PostMapping("/create-order")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest) {
        User user = userService.getUserById(orderRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found")); // throw an exception if the Optional is empty
        Orders order = orderService.createOrder(orderRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


}

