package com.example.product.entities.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest{
        private Integer id;

        @NotNull(message = "Product name is required")
        private String name;

        @NotNull(message = "Product description is required")
        private String description;

        @Positive(message = "Product available quantity must be greater than 0")
        private double availableQuantity;

        @Positive(message = "Product price must be greater than 0")
        private BigDecimal price;

        @NotNull(message = "Product category id is required")
        private Integer categoryId;
}
