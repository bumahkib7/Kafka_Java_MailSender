package com.bbmk.kafka.kafkaemail.request;

import com.bbmk.kafka.kafkaemail.models.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderRequest extends Orders {
    private Long userId;
    private String productName;
    private BigDecimal price;
}
