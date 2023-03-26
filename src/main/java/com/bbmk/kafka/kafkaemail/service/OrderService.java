package com.bbmk.kafka.kafkaemail.service;

import com.bbmk.kafka.kafkaemail.kafka.producer.OrderProducer;
import com.bbmk.kafka.kafkaemail.models.Order;
import com.bbmk.kafka.kafkaemail.repository.OrderRepository;
import com.bbmk.kafka.kafkaemail.request.EmailRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderProducer orderProducer;

    private final NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
        this.notificationService = notificationService;
    }

    public Order createOrder(Order order) {
        order = orderRepository.save(order);
        orderProducer.sendOrderEvent(order);
        sendOrderConfirmationEmail(order);
        return order;
    }

    private void sendOrderConfirmationEmail(Order order) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(order.getUser().getEmail());
        emailRequest.setSubject("Order Confirmation");
        emailRequest.setText("Hello " + order.getUser().getName() +
                             ",\n\nYour order for " +
                             order.getProductName() +
                             " has been received and is being processed.\n\nBest regards,\nThe Team");
        notificationService.sendNotification(emailRequest);
    }

    public void processOrderEvent(Order order) {
        // Process order event received from Kafka
    }
}
