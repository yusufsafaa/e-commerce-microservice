package com.example.ecommerce.entities.requests;

import com.example.ecommerce.entities.concretes.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
        private String id ;

        @NotNull(message = "Customer firstname cannot be null")
        private String firstname;

        @NotNull(message = "Customer lastname cannot be null")
        private String lastname;

        @NotNull(message = "Customer email cannot be null")
        @Email(message = "Customer email should be valid")
        private String email;

        private Address address;
}
