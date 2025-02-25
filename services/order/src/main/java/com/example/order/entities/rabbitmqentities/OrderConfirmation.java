package com.example.order.entities.rabbitmqentities;

import com.example.order.entities.enums.PaymentMethod;
import com.example.order.entities.responses.CustomerResponse;
import com.example.order.entities.responses.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customer;
    private List<PurchaseResponse> products;
}
