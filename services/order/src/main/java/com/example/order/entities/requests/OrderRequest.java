package com.example.order.entities.requests;

import com.example.order.entities.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest{
        private Integer id;

        private String reference;

        @Positive(message = "Amount should be greater than 0")
        private BigDecimal totalAmount;

        @NotNull(message = "Payment method should not be null")
        private PaymentMethod paymentMethod;

        @NotNull(message = "Customer id should not be null")
        @NotEmpty
        @NotBlank
        private String customerId;

        @NotEmpty(message = "You should purchase at least one product")
        private List<PurchaseRequest> products;
}
