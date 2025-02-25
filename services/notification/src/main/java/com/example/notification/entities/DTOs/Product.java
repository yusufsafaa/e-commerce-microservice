package com.example.notification.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
