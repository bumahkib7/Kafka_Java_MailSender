package com.bbmk.kafka.kafkaemail.service;

import com.bbmk.kafka.kafkaemail.kafka.producer.OrderProducer;
import com.bbmk.kafka.kafkaemail.models.Orders;
import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.repository.OrderRepository;
import com.bbmk.kafka.kafkaemail.repository.UserRepository;
import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import com.bbmk.kafka.kafkaemail.request.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderProducer orderProducer;

    private final NotificationService notificationService;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer, NotificationService notificationService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    public Orders createOrder(Orders orderRequest, User user) {
        Orders order = new Orders();
        order.setUser(user);
        order.setProductName(orderRequest.getProductName());
        order.setPrice(orderRequest.getPrice());
        order = orderRepository.save(order);
        orderProducer.sendOrderEvent(order);
        sendOrderConfirmationEmail((OrderRequest) order);
        return order;
    }


    public void sendOrderConfirmationEmail(OrderRequest order) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(order.getUser().getEmail());
        emailRequest.setSubject("Order Confirmation");
        emailRequest.setText("Hello " + order.getUserId() +
                             ",\n\nYour order for " +
                             order.getProductName() +
                             " has been received and is being processed.\n\nBest regards,\nThe Team");
        notificationService.sendNotification(emailRequest);
    }


}
