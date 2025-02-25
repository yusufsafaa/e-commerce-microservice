package com.example.product.entities.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequest{
    @NotNull(message = "Product id is required")
    private Integer productId;

    @NotNull(message = "Product quantity is required")
    private double quantity;
}
