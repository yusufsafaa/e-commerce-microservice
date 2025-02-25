package com.example.payment.repositories;

import com.example.payment.entities.concrete.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
