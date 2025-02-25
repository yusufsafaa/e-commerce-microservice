package com.example.order.core.clients;

import com.example.order.entities.responses.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/get/{customerId}")
    Optional<CustomerResponse> getCustomerById(@PathVariable String customerId);
}
