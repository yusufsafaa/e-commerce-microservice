package com.example.order.core.clients;

import com.example.order.entities.requests.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {
    @PostMapping("/create")
    Integer requestOrderPayment(@RequestBody PaymentRequest paymentRequest);
}
