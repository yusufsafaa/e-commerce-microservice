package com.example.order.entities.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
        @NotNull(message = "Product id should not be null")
        private Integer productId;

        @Positive(message = "Quantity should be greater than 0")
        private  double quantity;
}
