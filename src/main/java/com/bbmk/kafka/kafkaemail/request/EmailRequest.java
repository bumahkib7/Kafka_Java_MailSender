package com.bbmk.kafka.kafkaemail.request;

import lombok.Data;

@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String text;

    // Getters and setters
}
