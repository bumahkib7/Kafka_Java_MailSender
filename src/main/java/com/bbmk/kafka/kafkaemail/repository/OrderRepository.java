package com.bbmk.kafka.kafkaemail.repository;

import com.bbmk.kafka.kafkaemail.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
