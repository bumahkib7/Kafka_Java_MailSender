package com.bbmk.kafka.kafkaemail.controller;

import com.bbmk.kafka.kafkaemail.models.User;
import com.bbmk.kafka.kafkaemail.request.UserRequest;
import com.bbmk.kafka.kafkaemail.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = new User(userRequest.getName(), userRequest.getEmail());
        user = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
