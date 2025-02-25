package com.example.ecommerce.controllers;

import com.example.ecommerce.business.CustomerService;
import com.example.ecommerce.entities.requests.CustomerRequest;
import com.example.ecommerce.entities.responses.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.updateCustomer(customerRequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> customerExists(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.customerExists(customerId));
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }
}
