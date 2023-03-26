package com.bbmk.kafka.kafkaemail.repository;

import com.bbmk.kafka.kafkaemail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
