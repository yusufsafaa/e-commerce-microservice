package com.example.ecommerce.business;

import com.example.ecommerce.entities.concretes.Customer;
import com.example.ecommerce.entities.requests.CustomerRequest;
import com.example.ecommerce.entities.responses.CustomerResponse;
import com.example.ecommerce.core.exceptions.CustomerNotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public String createCustomer(@Valid CustomerRequest customerRequest) {
        return customerRepository.save(modelMapper.map(customerRequest, Customer.class)).getId();
    }

    public String updateCustomer(@Valid CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer cannot be updated: Customer not found"));

        modelMapper.map(customerRequest, customer);
        return customerRepository.save(customer).getId();
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }

    public Boolean customerExists(String customerId) {
        return customerRepository.existsById(customerId);
    }

    public CustomerResponse getCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        return modelMapper.map(customer, CustomerResponse.class);
    }

    public String deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
        return "Customer deleted successfully";
    }
}
