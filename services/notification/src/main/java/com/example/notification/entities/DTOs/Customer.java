package com.example.notification.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer{
    private String id;

    private String firstname;

    private String lastname;

    private String email;
}
