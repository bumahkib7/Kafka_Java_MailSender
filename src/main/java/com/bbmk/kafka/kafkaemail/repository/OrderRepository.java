package com.bbmk.kafka.kafkaemail.repository;

import com.bbmk.kafka.kafkaemail.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
