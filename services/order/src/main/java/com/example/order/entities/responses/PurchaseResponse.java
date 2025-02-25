package com.example.order.entities.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse{
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
